package com.pl.airboss.crm.res.dao;

import com.pl.airboss.crm.res.bean.ResPatternDefineBean;
import com.pl.airboss.crm.res.bean.ResPatternDefineQueryRspBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResPatternDefineBeanMapper {
    int deleteByPrimaryKey(Long patternDefId);

    int insert(ResPatternDefineBean record);

    int insertSelective(ResPatternDefineBean record);

    ResPatternDefineBean selectByPrimaryKey(Long patternDefId);

    List<ResPatternDefineBean> selectList(ResPatternDefineBean bean);

    List<ResPatternDefineQueryRspBean> selectDefineList(ResPatternDefineBean bean);

    int updateByPrimaryKeySelective(ResPatternDefineBean record);

    int updateByPrimaryKey(ResPatternDefineBean record);

    int changeStatus(ResPatternDefineBean record);

    boolean checkPatternDefNameUnique(@Param("patternDefName") String patternDefName, @Param("patternDefId") Long patternDefId);

    int deleteNumPatternByIds(Long[] numPatternIds);
}