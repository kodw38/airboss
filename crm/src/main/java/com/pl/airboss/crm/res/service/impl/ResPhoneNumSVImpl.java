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
import com.pl.airboss.framework.cache.impl.CacheManager;
import com.pl.airboss.utils.Convert;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.cache.CacheCfgSystem;
import com.pl.airboss.web.cache.CacheResPatternDefine;
import com.pl.airboss.web.cache.CacheStaticData;
import com.pl.airboss.web.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
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
                    b.setOpId(Long.valueOf(op.getOpId()));//当前操作员
                    b.setResStatus(ResConst.PHONE_NUM_STATUS_G);//生成
                    b.setPassword(pwd);//密码
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
}
