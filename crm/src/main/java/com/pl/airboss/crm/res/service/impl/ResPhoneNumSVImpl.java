package com.pl.airboss.crm.res.service.impl;/**
 * Created by admin on 2020/9/9.
 */

import com.pl.airboss.crm.res.bean.*;
import com.pl.airboss.crm.res.dao.ResPatternDefineBeanMapper;
import com.pl.airboss.crm.res.dao.ResPatternSegmentBeanMapper;
import com.pl.airboss.crm.res.dao.ResPhoneNumOriginBeanMapper;
import com.pl.airboss.crm.res.dao.ResSelpriceModeBeanMapper;
import com.pl.airboss.crm.res.service.interfaces.IResPhoneNumSV;
import com.pl.airboss.crm.utils.PatternMatch;
import com.pl.airboss.crm.utils.Utils;
import com.pl.airboss.framework.bean.BusinessException;
import com.pl.airboss.framework.cache.impl.CacheManager;
import com.pl.airboss.utils.Convert;
import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.cache.CacheCfgSystem;
import com.pl.airboss.web.cache.CacheResPatternDefine;
import com.pl.airboss.web.cache.CacheStaticData;
import com.pl.airboss.web.service.impl.SysServiceImpl;
import com.pl.airboss.web.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ResPhoneNumSVImpl
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/9/9 13:15
 * @Version 1.0
 **/
@Service
public class ResPhoneNumSVImpl implements IResPhoneNumSV {
    transient static Logger log = LoggerFactory.getLogger(ResPhoneNumSVImpl.class);

    @Autowired
    ResPatternDefineBeanMapper resPatternDefineBeanMapper;

    @Autowired
    ResPatternSegmentBeanMapper resPatternSegmentBeanMapper;

    @Autowired
    ResPhoneNumOriginBeanMapper resPhoneNumOriginBeanMapper;

    @Autowired
    ResSelpriceModeBeanMapper resSelpriceModeBeanMapper;

    @Autowired
    CacheManager cacheManager;


    /**
     * 查询号码模式列表
     * @param bean
     * @return
     */
    public List<ResPatternDefineBean> queryNumPatternDefineList(ResPatternDefineBean bean){
        return resPatternDefineBeanMapper.selectList(bean);
    }

    /**
     * 查询号码模式
     * @param defId
     * @return
     */
    public ResPatternDefineBean queryNumPatternDefine(long defId){
        return resPatternDefineBeanMapper.selectByPrimaryKey(defId);
    }

    /**
     * 增加号码模式
     * @param bean
     * @return
     */
    public int addNumPatternDefine(ResPatternDefineBean bean){
        return resPatternDefineBeanMapper.insert(bean);
    }

    /**
     * 修改号码模式
     * @param bean
     * @return
     */
    public int updateNumPatternDefine(ResPatternDefineBean bean){
        return resPatternDefineBeanMapper.updateByPrimaryKey(bean);
    }

    /**
     * 删除号码模式
     * @param defId
     * @return
     */
    public int deleteNumPatternDefine(long defId){
        return resPatternDefineBeanMapper.deleteByPrimaryKey(defId);
    }

    @Override
    public List<ResSelpriceModeBean> queryPriceList() {
        List<ResSelpriceModeBean> ret = resSelpriceModeBeanMapper.selectList();
        return ret;
    }

    public ResSelpriceModeBean queryPriceByPrimaryKey(Long id){

        return resSelpriceModeBeanMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePrice(ResSelpriceModeBean bean) {
        return resSelpriceModeBeanMapper.updateByPrimaryKey(bean);
    }

    public int addPrice(ResSelpriceModeBean bean){
        return resSelpriceModeBeanMapper.insert(bean);
    }

    @Override
    public int deletePrice(Long key) {
        return resSelpriceModeBeanMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int importSegmentList(List<ResPatternSegmentBean> records) {

        return resPatternSegmentBeanMapper.insertList(records);
    }

    public int addSegmentPattern(ResPatternSegmentBean bean){
        return resPatternSegmentBeanMapper.insert(bean);
    }

    public ResPatternSegmentBean querySegmentPattern(long redic){
        return resPatternSegmentBeanMapper.selectByPrimaryKey(redic);
    }

    @Override
    public List<ResPatternSegmentBean> querySegmentList(ResPatternSegmentBean cond) {
        return resPatternSegmentBeanMapper.selectList(cond);
    }

    @Override
    public int updateSegment(ResPatternSegmentBean bean) {
        return resPatternSegmentBeanMapper.updateByPrimaryKey(bean);
    }

    @Override
    public int deleteSegment(long beanid) {
        return resPatternSegmentBeanMapper.deleteByPrimaryKey(beanid);
    }

    /**
     * 根据号段生成号码
     * @param segmentId
     * @return
     * @throws Exception
     */
    @Override
    public boolean generatorNumBySegment(long segmentId) throws Exception{
        ResPatternSegmentBean seg = resPatternSegmentBeanMapper.selectByPrimaryKey(segmentId);
        if(null != seg){
            String exp = seg.getSegExp();
            //判断号码表是否已经存在满足规则的号码，如果已经存在则不能用改号段生成号码
            if(resPhoneNumOriginBeanMapper.matchRegexp(exp)>0){
                throw new Exception("该号段下已经有生成的号码存在，不能使用改号段再次生成号码");
            }
            //根据正则表达式生成号码列表
            List<String> ls = Utils.getListByRegexp(exp);
            if(null !=ls && ls.size()>0){
                List<ResPhoneNumOriginBean> nums = new ArrayList<>();
                Date now = new Date();
                SecOperatorBean op = ShiroUtils.getSysUser();
                String pwd = Convert.toStr(cacheManager.getMapCacheValue(CacheCfgSystem.class.getName(),"res.num_default_password"));
                for(String n:ls){
                    ResPhoneNumOriginBean b = new ResPhoneNumOriginBean();
                    b.setResId(n);
                    b.setStockInDate(now); //
                    b.setPatternSegId(segmentId);//PATTERN_SEG_ID模式号段编号
                    b.setPatternDefId(matchPatternDef(n));//PATTERN_DEF_ID号码模式编号
                    b.setRsrvStr2(n);
                    if (op.getOpId() != null && !"".equals(op.getOpId())) {
                        b.setOpId(Long.valueOf(op.getOpId()));//当前操作员
                    }
                    b.setResStatus(ResConst.PHONE_NUM_STATUS_G);//生成
                    b.setPassword(pwd);//密码
                    nums.add(b);
                }
                resPhoneNumOriginBeanMapper.insertList(nums);
                return true;
            }
        }
        return false;
    }

    //从号码模式缓存中获取匹配改号码的模式编号
    long matchPatternDef(String num)throws Exception{
        List<ResPatternDefineBean> ls = (List)cacheManager.getCache(CacheResPatternDefine.class.getName());
        if(null != ls){
            for(ResPatternDefineBean b:ls){
                if(PatternMatch.matchExp(num,b.getBitRel(),"")){
                    return b.getPatternDefId();
                }
            }
        }
        return -1;

    }



    @Override
    public boolean cleanNumBySegment(long segmentId) throws Exception{
        ResPatternSegmentBean seg = resPatternSegmentBeanMapper.selectByPrimaryKey(segmentId);
        if(null != seg) {
            if (resPhoneNumOriginBeanMapper.matchRegexpWithoutStateGEND(segmentId)>0) {
                throw new Exception("该号段下生成的号码状态已近发生变化，不能使用改号段清理号码");
            }
            resPhoneNumOriginBeanMapper.deleteBySegId(segmentId);
            return true;
        }
        return false;
    }

    @Override
    public List<ResPhoneNumOriginBean> queryNum(ResPhoneNumOriginBean bean,int start,int end) {

        return resPhoneNumOriginBeanMapper.selectList(bean,start,end);
    }

    public ResPhoneNumOriginBean queryNumById(String recid){
        return resPhoneNumOriginBeanMapper.selectByPrimaryKey(recid);
    }

    @Override
    public boolean updateNum(ResPhoneNumOriginBean bean) {
        resPhoneNumOriginBeanMapper.updateByPrimaryKey(bean);
        return true;
    }

    @Override
    public boolean deleteNum(String beanid) {
        resPhoneNumOriginBeanMapper.deleteByPrimaryKey(beanid);
        return true;
    }

    /**
     * 号码预占，修改RES_STATUS=O
     * 使用done_code（int）做唯一操作
     * @param phoneNum
     * @return
     */
    public int occupyPhoneNum(String phoneNum)throws Exception{
        ResPhoneNumOriginBean b= resPhoneNumOriginBeanMapper.selectByPrimaryKey(phoneNum);
        if(null != b){
            if(!b.getResStatus().equalsIgnoreCase(ResConst.PHONE_NUM_STATUS_G)){
                throw new Exception(phoneNum+" 该号码已经被使用，请选择其他号码");
            }
            long done = b.getDoneCode();
            long nextdone = done+1;
            b.setDoneCode(nextdone);  //增加操作批号
            b.setResStatus(ResConst.PHONE_NUM_STATUS_O); //设置号码为预占
            ResPhoneNumOriginBean cond = new ResPhoneNumOriginBean();
            cond.setResId(b.getResId());
            cond.setResStatus(ResConst.PHONE_NUM_STATUS_G);
            cond.setDoneCode(done);
            int n = resPhoneNumOriginBeanMapper.update(b,cond);
            if(n==0){
                throw new Exception(phoneNum+" 该号码已经被使用，请选择其他号码");
            }
            return n;
        }else{
            throw new Exception(phoneNum+" 没有该号码，请核查");
        }
    }

    /**
     * 号码释放
     * @param phoneNum
     * @return
     */
    public int releasePhoneNum(String phoneNum)throws Exception{
        ResPhoneNumOriginBean b= resPhoneNumOriginBeanMapper.selectByPrimaryKey(phoneNum);
        if(null != b){
            if(!b.getResStatus().equalsIgnoreCase(ResConst.PHONE_NUM_STATUS_O)){
                if(b.getResStatus().equalsIgnoreCase(ResConst.PHONE_NUM_STATUS_G)) {
                    throw new Exception(phoneNum + " 该号码已经释放");
                }else{
                    throw new Exception(phoneNum + " 该号码已经被使用，不能释放");
                }
            }
            long done = b.getDoneCode();
            long nextdone = done+1;
            b.setDoneCode(nextdone);  //增加操作批号
            b.setResStatus(ResConst.PHONE_NUM_STATUS_G); //设置号码为预占
            ResPhoneNumOriginBean cond = new ResPhoneNumOriginBean();
            cond.setResId(b.getResId());
            cond.setResStatus(ResConst.PHONE_NUM_STATUS_O);
            cond.setDoneCode(done);
            int n = resPhoneNumOriginBeanMapper.update(b,cond);
            if(n==0){
                throw new Exception(phoneNum+" 该号码释放失败");
            }
            return n;
        }else{
            throw new Exception(phoneNum+" 没有该号码，请核查");
        }
    }

    @Override
    public int changeStatus(ResPatternSegmentBean record) {
        return resPatternSegmentBeanMapper.changeStatus(record);
    }

    @Override
    public boolean checkPatternSegNameUnique(String segName, Long patternSegId) {
        return resPatternSegmentBeanMapper.checkPatternSegNameUnique(segName,patternSegId);
    }

    @Override
    public int deleteSegPatternByIds(String ids) {
        Long[] segPatternIds = Convert.toLongArray(ids);
        return resPatternSegmentBeanMapper.deleteSegPatternByIds(segPatternIds);
    }

    @Override
    public String importSegment(List<ResPatternSegmentBean> segmentList, boolean updateSupport) {
        if (StringUtils.isNull(segmentList) || segmentList.size() == 0) {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        for (ResPatternSegmentBean segment : segmentList) {
            //segment.setEffectiveDate(sf.parse((String)segment.get("生效时间")));
           // segment.setExpireDate(sf.parse((String)m.get("失效时间")));
            try {
                //验证号段是否存在
                Boolean exist = resPatternSegmentBeanMapper.checkPatternSegNameUnique(segment.getPatternSegName(), null);
                if (!exist) {//不存在
                    segment.setState("1");
                    segment.setCreateDate(new Date());
                    segment.setResType(1L);
                    resPatternSegmentBeanMapper.insert(segment);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、号段名称 " + segment.getPatternSegName()+ " 导入成功");

                } else if (updateSupport) {//存在更新
                    resPatternSegmentBeanMapper.updateByPrimaryKey(segment);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、号段名称 " + segment.getPatternSegName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、号段名称 " + segment.getPatternSegName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、号段名称 " + segment.getPatternSegName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public List<ResPatternSegmentBean> selectList(ResPatternSegmentBean segment) {
        return resPatternSegmentBeanMapper.selectList(segment);
    }




    @Override
    public int changeStatus(ResPatternDefineBean record) {
        return resPatternDefineBeanMapper.changeStatus(record);
    }

    @Override
    public boolean checkPatternDefNameUnique(String patternDefName, Long patternDefId) {
        return resPatternDefineBeanMapper.checkPatternDefNameUnique(patternDefName,patternDefId);
    }

    @Override
    public int deleteNumPatternByIds(String ids) {
        Long[] numPatternIds = Convert.toLongArray(ids);
        return resPatternDefineBeanMapper.deleteNumPatternByIds(numPatternIds);
    }

    @Override
    public List<ResPatternDefineBean> selectList(ResPatternDefineBean numPattDef) {
        return resPatternDefineBeanMapper.selectList(numPattDef);
    }
}
