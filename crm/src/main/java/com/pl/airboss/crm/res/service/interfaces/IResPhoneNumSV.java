package com.pl.airboss.crm.res.service.interfaces;


import com.pl.airboss.crm.res.bean.*;
import com.pl.airboss.web.bean.SecOperatorBean;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IResPhoneNumSV {

    /**
     * 查询号码模式列表
     * @param bean
     * @return
     */
    public List<ResPatternDefineBean> queryNumPatternDefineList(ResPatternDefineBean bean);

    /**
     * 查询号码模式
     * @param defId
     * @return
     */
    public ResPatternDefineBean queryNumPatternDefine(long defId);

    /**
     * 增加号码模式
     * @param bean
     * @return
     */
    public int addNumPatternDefine(ResPatternDefineBean bean);

    /**
     * 修改号码模式
     * @param bean
     * @return
     */
    public int updateNumPatternDefine(ResPatternDefineBean bean);

    /**
     * 删除号码模式
     * @param defId
     * @return
     */
    public int deleteNumPatternDefine(long defId);


    /**
     *查询价格模式列表
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:06
     *@Param
     *@return
     *@Version 1.0
     */
    public List<ResSelpriceModeBean> queryPriceList(ResSelpriceModeBean bean);

    public ResSelpriceModeBean queryPriceByPrimaryKey(Long id);
    /**
     *价格模式
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:07
     *@Param
     *@return
     *@Version 1.0
     */
    public int addPrice(ResSelpriceModeBean bean);

    /**
     *修改价格模式
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:07
     *@Param
     *@return
     *@Version 1.0
     */
    public int updatePrice(ResSelpriceModeBean bean);

    /**
     *删除价格模式，如果已经被用不能被删除
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:07
     *@Param
     *@return
     *@Version 1.0
     */
    public int deletePrice(Long key);

    /**
     *从文件导入号段信息
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:08
     *@Param
     *@return
     *@Version 1.0
     */
    public int importSegmentList(List<ResPatternSegmentBean> records);

    public int addSegmentPattern(ResPatternSegmentBean bean);

    public ResPatternSegmentBean querySegmentPattern(long redic);


    /**
     *查询号段列表
     *@Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:02
     *@Param
     *@return
     *@Version 1.0
     */
    public List<ResPatternSegmentBean> querySegmentList(ResPatternSegmentBean cond);

    /**
     *修改号段信息
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:09
     *@Param
     *@return
     *@Version 1.0
     */
    public int updateSegment(ResPatternSegmentBean bean);

    /**
     *删除号段信息
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:09
     *@Param
     *@return
     *@Version 1.0
     */
    public int deleteSegment(long beanid);

    /**
     *根据号段生成号码
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:04
     *@Param
     *@return
     *@Version 1.0
     */
    public boolean generatorNumBySegment(long segmentId) throws Exception;

    /**
     *清除根据号段生成的号码，如果有一个号码状态变化，则不能清除。
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:08
     *@Param
     *@return
     *@Version 1.0
     */
    public boolean cleanNumBySegment(long segmentId)throws Exception;


    /**
     *查询号码
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:04
     *@Param
     *@return
     *@Version 1.0
     */
    public List<ResPhoneNumOriginBean> queryNum(ResPhoneNumOriginBean bean,int offSet,int limit);

    /*
    * 查询号码 关联segment和pattern表
    * */
    public List<ResPhoneNumQueryRspBean> queryNumList(ResPhoneNumOriginBean bean,int offSet,int limit);

    public ResPhoneNumOriginBean queryNumById(String recid);
    /**
     *变更号码，包括填补资料，号码状态变更
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:05
     *@Param
     *@return
     *@Version 1.0
     */
    public boolean updateNum(ResPhoneNumOriginBean bean);

    /**
     *删除号码，如果号码状态是未用状态
     * @Description
     *@auther Kod Wong
     *@Date 2020/9/9 13:05
     *@Param
     *@return
     *@Version 1.0
     */
    public boolean deleteNum(String beanid);


    /**
     * 号码预占
     * @param phoneNum
     * @return
     */
    public int occupyPhoneNum(String phoneNum)throws Exception;

    /**
     * 号码释放
     * @param phoneNum
     * @return
     */
    public int releasePhoneNum(String phoneNum)throws Exception;

    /**
     * 状态修改
     * @param record
     * @return
     */
    public int changeStatus(ResPatternSegmentBean record);

    /**
     * 校验segName是否存在
     * @param segName
     * @return
     */
    public boolean checkPatternSegNameUnique(String segName, Long patternSegId);

    /**
     * 批量删除号段规则
     * @param ids
     * @return
     */
    public int deleteSegPatternByIds(String ids);

    /**
     * 导入号段
     * @param segmentList
     * @return
     */
    public String importSegment(List<ResPatternSegmentBean> segmentList, boolean updateSupport);

    /**
     * 导出号段
     * @param segment
     * @return
     */
    List<ResPatternSegmentBean> selectList(ResPatternSegmentBean segment);





    /**
     * 号码模式状态修改
     * @param record
     * @return
     */
    public int changeStatus(ResPatternDefineBean record);

    /**
     * 校验patternDefName是否存在
     * @param patternDefName
     * @return
     */
    public boolean checkPatternDefNameUnique(String patternDefName,Long patternDefId);

    /**
     * 批量删除号码模式规则
     * @param ids
     * @return
     */
    public int deleteNumPatternByIds(String ids);

    /**
     * 导出号码模式
     * @param numPattDef
     * @return
     */
    List<ResPatternDefineBean> selectList(ResPatternDefineBean numPattDef);

    /**
     * 校验资费名称是否存在
     * @param modeDesc
     * @return
     */
    boolean checkPatternPriceNameUnique(String modeDesc, Long resSpecId);
}
