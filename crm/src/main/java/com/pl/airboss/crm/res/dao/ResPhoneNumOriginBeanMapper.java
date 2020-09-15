package com.pl.airboss.crm.res.dao;

import com.pl.airboss.crm.res.bean.ResPhoneNumOriginBean;
import com.pl.airboss.crm.res.bean.ResPhoneNumOriginBeanKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResPhoneNumOriginBeanMapper {
    int deleteByPrimaryKey(String key);

    int insert(ResPhoneNumOriginBean record);

    boolean insertList(List<ResPhoneNumOriginBean> records);

    int insertSelective(ResPhoneNumOriginBean record);

    ResPhoneNumOriginBean selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(ResPhoneNumOriginBean record);

    int updateByPrimaryKey(ResPhoneNumOriginBean record);

    int update(@Param("bean") ResPhoneNumOriginBean bean,@Param("cond") ResPhoneNumOriginBean cond);

    int matchRegexp(String exp);

    int matchRegexpWithoutStateGEND(@Param("segmentId") long segmentId);

    int deleteBySegId(long segId);

    List<ResPhoneNumOriginBean> selectList(@Param("bean") ResPhoneNumOriginBean b,@Param("offSet") int start,@Param("limit") int end);
}