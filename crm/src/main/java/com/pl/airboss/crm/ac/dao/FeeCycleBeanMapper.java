package com.pl.airboss.crm.ac.dao;

import com.pl.airboss.crm.ac.bean.FeeCycleBean;

import java.util.List;

public interface FeeCycleBeanMapper {
    int deleteByPrimaryKey(Short cycleRuleId);

    int insert(FeeCycleBean record);

    int insertSelective(FeeCycleBean record);

    FeeCycleBean selectByPrimaryKey(Short cycleRuleId);

    int updateByPrimaryKeySelective(FeeCycleBean record);

    int updateByPrimaryKey(FeeCycleBean record);

    List<FeeCycleBean> list();


}