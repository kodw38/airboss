package com.pl.airboss.crm.res.dao;

import com.pl.airboss.crm.res.bean.ResPatternSegmentBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResPatternSegmentBeanMapper {
    int deleteByPrimaryKey(Long patternSegId);

    int insert(ResPatternSegmentBean record);

    int insertList(List<ResPatternSegmentBean> list);

    int insertSelective(ResPatternSegmentBean record);

    ResPatternSegmentBean selectByPrimaryKey(Long patternSegId);

    List<ResPatternSegmentBean> selectList(ResPatternSegmentBean bean);

    int updateByPrimaryKeySelective(ResPatternSegmentBean record);

    int updateByPrimaryKey(ResPatternSegmentBean record);

    int changeStatus(ResPatternSegmentBean record);

    Boolean checkPatternSegNameUnique(@Param("segName") String segName, @Param("patternSegId") Long patternSegId);

    int deleteSegPatternByIds(Long[] ids);


}