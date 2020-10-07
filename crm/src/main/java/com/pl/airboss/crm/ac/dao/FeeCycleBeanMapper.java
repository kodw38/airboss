package com.pl.airboss.crm.ac.dao;

import com.pl.airboss.crm.ac.bean.FeeCycleBean;

public interface FeeCycleBeanMapper {
    int deleteByPrimaryKey(Short cycleRuleId);

    int insert(FeeCycleBean record);

    int insertSelective(FeeCycleBean record);

    FeeCycleBean selectByPrimaryKey(Short cycleRuleId);

    int updateByPrimaryKeySelective(FeeCycleBean record);

    int updateByPrimaryKey(FeeCycleBean record);
}