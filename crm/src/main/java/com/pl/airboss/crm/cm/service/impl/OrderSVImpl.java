package com.pl.airboss.crm.cm.service.impl;

import com.pl.airboss.crm.cm.bean.*;
import com.pl.airboss.crm.cm.dao.*;
import com.pl.airboss.crm.cm.service.interfaces.IOrderSV;
import com.pl.airboss.crm.product.bean.ProductBean;
import com.pl.airboss.crm.product.bean.ProductInfo;
import com.pl.airboss.crm.product.bean.ServiceBean;
import com.pl.airboss.crm.product.bean.ServiceParamBean;
import com.pl.airboss.crm.product.dao.ProductBeanMapper;
import com.pl.airboss.crm.product.service.interfaces.IOfferSV;
import com.pl.airboss.crm.utils.TelnetOperator;
import com.pl.airboss.framework.aspectj.LogAspect;
import com.pl.airboss.framework.cache.impl.CacheManager;
import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.cache.CacheCfgSystem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderSVImpl implements IOrderSV {
    static transient Log log = LogFactory.getLog(OrderSVImpl.class);

    @Autowired
    CustomerBeanMapper customerBeanMapper;

    @Autowired
    CustPersonBeanMapper personBeanMapper;

    @Autowired
    CustGroupBeanMapper groupBeanMapper;

    @Autowired
    UserBeanMapper userBeanMapper;

    @Autowired
    ProductBeanMapper productBeanMapper;

    @Autowired
    UserMemberBeanMapper userMemberBeanMapper;

    @Autowired
    AccountUserBeanMapper accountUserBeanMapper;

    @Autowired
    AccountBeanMapper accountBeanMapper;

    @Autowired
    IOfferSV offerSV;

    @Autowired
    CacheManager cacheManager;

    public CustPersonBean queryIndividualCustomer(String psptTypeCode, String psptId){
        CustomerBean b = new CustomerBean();
        b.setPsptTypeCode(psptTypeCode);
        b.setPsptId(psptId);
        List<CustomerBean> q = customerBeanMapper.select(b);
        if(null != q && q.size()==1) {
            CustomerBean c = q.get(0);
            if ("0".equalsIgnoreCase(c.getCustType())) {
                //个人客户
                CustPersonBean cq = new CustPersonBean();
                cq.setPsptTypeCode(psptTypeCode);
                cq.setPsptId(psptId);
                List<CustPersonBean> list = personBeanMapper.select(cq);
                if(null != list && list.size()==1){
                    return list.get(0);
                }
            }
        }
        return null;
    }

    public CustGroupBean queryGroupCustomer(String psptTypeCode, String psptId){
        CustomerBean b = new CustomerBean();
        b.setPsptTypeCode(psptTypeCode);
        b.setPsptId(psptId);
        List<CustomerBean> q = customerBeanMapper.select(b);
        if(null != q && q.size()==1) {
            CustomerBean c = q.get(0);
            if ("0".equalsIgnoreCase(c.getCustType())) {
                //个人客户
                CustGroupBean cq = new CustGroupBean();
                cq.setPsptTypeCode(psptTypeCode);
                cq.setPsptId(psptId);
                List<CustGroupBean> list = groupBeanMapper.select(cq);
                if(null != list && list.size()==1){
                    return list.get(0);
                }
            }
        }
        return null;
    }


    public List<UserBean> queryUserByCustId(Long custId){
        List<UserBean> ls = userBeanMapper.selectUserListByCustId(custId);
        if(null != ls){
            for(UserBean l:ls){
                CustGroupBean m = userMemberBeanMapper.selectGroupCustByUserId(l.getUserId());
                if(null != m) {
                    l.setGroupCustId(m.getCustId());
                    l.setGroupCustName(m.getCustName());
                }
            }
        }
        return ls;
    }

    public List<UserBean> queryGroupUserByGroupCustId(Long custId){
        return groupBeanMapper.selectUsers(custId);
    }
    public List<AccountBean> queryCustAccount(Long custId){
        return accountBeanMapper.selectByCustId(custId);
    }

    public List<CustGroupBean> queryCustGroup(CustGroupBean cond){
        return groupBeanMapper.select(cond);
    }
    public boolean payment(Double fee){
        //todo 财务接口
        return true;
    }

    @Transactional
    public Long submitSubscribe(CustPersonBean personBean,String phoneNumber,Integer productId,Long accountId,Long groupCustId,Long groupRoleId,Integer prepayTag,String[] filePaths)throws Exception{
        //客户数据TF_F_CUSTOMER
        CustomerBean baseCust = new CustomerBean();
        if(StringUtils.isEmpty(personBean.getPsptId())) throw new Exception("需要填入证件号码");
        baseCust.setPsptId(personBean.getPsptId());
        if(StringUtils.isEmpty(personBean.getPsptTypeCode())) throw new Exception("需要选择证件类型");
        baseCust.setPsptTypeCode(personBean.getPsptTypeCode());
        if(StringUtils.isEmpty(personBean.getCustName())) throw new Exception("需要填入客户名称");
        baseCust.setCustName(personBean.getCustName());
        baseCust.setCustState("1");
        if(StringUtils.isEmpty(personBean.getCustType())) throw new Exception("需要选择客户类型");
        baseCust.setCustType(personBean.getCustType());
        customerBeanMapper.insert(baseCust);
        //个人客户信息TF_F_CUST_PERSON
        personBeanMapper.insert(personBean);
        //保存用户数据TF_F_USER
        ProductBean p = productBeanMapper.selectByPrimaryKey(productId);
        UserBean user = new UserBean();
        user.setAcctTag("0");//正常出账
        user.setCustId(baseCust.getCustId());
        user.setOpenDate(new Date());
        user.setOpenMode("0");//正常
        user.setServType(p.getNetTypeCode());
        user.setSerialNumber(phoneNumber+"");
        user.setPrepayTag(prepayTag+"");
        user.setProductId(productId);
        userBeanMapper.insert(user);
        //集团用户关系TF_F_USER_MEMBER
        if(groupCustId>0) {
            UserMemberBean um = new UserMemberBean();
            um.setVpnId(groupCustId);
            um.setMemberRoleType(groupRoleId+"");
            um.setMemberRoleNumber(phoneNumber);
            userMemberBeanMapper.insert(um);
        }
        //保存账户TF_F_ACCOUNT
        if(accountId>0){
            AccountUserBean au = new AccountUserBean();
            au.setAcctId(accountId);
            au.setUserId(user.getUserId());
            accountUserBeanMapper.insert(au);
        }else{
            AccountBean a = new AccountBean();
            a.setCustId(baseCust.getCustId());
            a.setOpenDate(new Date());
            a.setPayModeCode("0");
            a.setPayName(baseCust.getCustName());//账户名称用客户名称
            accountBeanMapper.insert(a);

            AccountUserBean au = new AccountUserBean();
            au.setAcctId(a.getAcctId());
            au.setUserId(user.getUserId());
            accountUserBeanMapper.insert(au);
        }
        //保存客户附件关系

        //产品、字符、服务不实例化，一旦产品、资费、服务被使用就不能修改，删除。
        //产品实例化
        //TF_F_USER_PRODUCT

        //TF_F_USER_FEEPOLICY
        //服务实例化
        //TF_F_USER_SERV
        //TF_F_USER_SERVPARAM

        //服务开通
        provision(user,productId);
        return user.getUserId();
    }

    private void provision(UserBean user,Integer productId)throws Exception{
        List<ServiceBean> ss = offerSV.queryProductService(productId);
        if(null != ss){
            for(ServiceBean b:ss){
                List<ServiceParamBean> ls = offerSV.queryServiceParams(b.getServiceId());
                if(null !=ls){
                    for(ServiceParamBean p:ls){
                        if("FIXLINE_ACTIVE".equalsIgnoreCase(p.getParamType())){
                            String ret = executeFixLineCommand(convert(p.getParamValue(),user));
                        }
                        if("BROADBAND_ACTIVE".equalsIgnoreCase(p.getParamType())){
                            //todo
                        }
                    }
                }
            }
        }
    }
    //--config modify subscriber dn {phoneNumber} operateout del outgoingrights local&ddd&idd
    //--config modify subscriber dn {phoneNumber} operateout add outgoingrights local&ddd&idd
    private String convert(String command,UserBean user){
        return command.replaceAll("\\{phoneNumber\\}",user.getSerialNumber());
    }

    private String executeFixLineCommand(String command){
        try {
            TelnetOperator to = getFixLineTelenetOperator();
            String ret = to.sendCommand(command);
            log.debug("execute Telenet Command:" + command + "\n " + ret);
            to.distinct();
            LogAspect.saveBusinessLog(this.getClass().getName()+"#executeFixLineCommand",command,null,ret);
            return ret;
        }catch (Exception e){
            LogAspect.saveBusinessLog(this.getClass().getName()+"#executeFixLineCommand",command,e,null);
            return "ERROR";
        }
    }

    private TelnetOperator getFixLineTelenetOperator(){
        String termtype = (String)cacheManager.getMapCacheValue(CacheCfgSystem.class.getName(),"TELETE_TERMTYPE");
        String prompt = (String)cacheManager.getMapCacheValue(CacheCfgSystem.class.getName(),"TELETE_PROMPT");
        String address = (String)cacheManager.getMapCacheValue(CacheCfgSystem.class.getName(),"FIXLINE_SERVER_ADDRESS");
        String user=null,pwd=null,ip=null,port=null;
        if(address.contains("@")){
            String[] ss = address.split("@");
            String[] u = ss[0].split("\\:");
            user=u[0].trim();
            pwd=u[1].trim();
            String[] m = ss[1].split("\\:");
            ip = m[0].trim();
            port=m[1].trim();
        }else{
            String[] m = address.split("\\:");
            ip = m[0].trim();
            port=m[1].trim();
        }
        TelnetOperator to=null;
        if(StringUtils.isNotEmpty(user)){
            to = new TelnetOperator();
            to.login(ip,Integer.parseInt(port),user,pwd);
        }
        else to = new TelnetOperator(ip,port);
        return to;
    }



    public List<UserBean> queryUser(UserBean bean){
        return userBeanMapper.selectList(bean);
    }

    @Transactional
    public int addUser2Group(Long userId,Long groupId,Long accountId,String roleType)throws Exception{
        UserBean u = userBeanMapper.selectByPrimaryKey(userId);
        if(null == u)throw new Exception("用户不存在:"+userId);
        //变更集团客户关系
        UserMemberBean b = userMemberBeanMapper.selectByPhoneNumberAndCustId(u.getSerialNumber(),groupId);
        if(null !=b){
            CustGroupBean g = groupBeanMapper.selectByCustId(groupId);
            String custName="";
            if(null !=g){
                custName=g.getCustName();
            }
            throw new Exception("用户:"+userId+" 已经归属集团客户 "+custName+",需要先解除，在加入到当前客户下");
        }
        UserMemberBean um = new UserMemberBean();
        um.setVpnId(groupId);
        um.setMemberRoleType(roleType);
        um.setStartDate(new Date());
        um.setMemberRoleNumber(u.getSerialNumber());
        userMemberBeanMapper.insert(um);

        //变更账户
        AccountUserBean a = accountUserBeanMapper.selectByUserIdAndAccountId(userId,accountId);
        if(null != a){
            AccountUserBeanKey key  = new AccountUserBeanKey();
            key.setAcctId(a.getAcctId());
            key.setUserId(a.getUserId());
            accountUserBeanMapper.deleteByPrimaryKey(key);
        }
        AccountUserBean na = new AccountUserBean();
        na.setUserId(userId);
        na.setAcctId(accountId);
        accountUserBeanMapper.insert(na);
        return 1;
    }

    public int addAccount(AccountBean bean){
        return accountBeanMapper.insert(bean);
    }

    public int addCustGroup(CustGroupBean bean){
        return groupBeanMapper.insertBase(bean);
    }

    @Transactional
    public int removeGroupCust(Long custId)throws Exception{
        List<UserMemberBean> ls = userMemberBeanMapper.selectUserMemberList(custId);
        if(null !=ls && ls.size()>0){
            throw new Exception("该集团客户下还有用户，不能销户");
        }
        List<AccountUserBean> as = accountUserBeanMapper.selectAccountUserListByCustId(custId);
        if(null !=as && as.size()>0){
            throw new Exception("该集团客户下的账户还有关联的用户，不能销户");
        }

        customerBeanMapper.delete(custId);
        groupBeanMapper.delete(custId);
        return 1;
    }

    @Override
    public List<CustGroupBean> queryGroupCustomer(CustGroupBean bean) {
        return groupBeanMapper.selectList(bean);
    }
}
