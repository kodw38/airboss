package com.pl.airboss.crm.res.dao;

import com.pl.airboss.crm.res.bean.ResPatternDefineBean;

import java.util.List;

public interface ResPatternDefineBeanMapper {
    int deleteByPrimaryKey(Long patternDefId);

    int insert(ResPatternDefineBean record);

    int insertSelective(ResPatternDefineBean record);

    ResPatternDefineBean selectByPrimaryKey(Long patternDefId);

    List<ResPatternDefineBean> selectList(ResPatternDefineBean bean);

    int updateByPrimaryKeySelective(ResPatternDefineBean record);

    int updateByPrimaryKey(ResPatternDefineBean record);
}