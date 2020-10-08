package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.FeepolicyParamBean;

public interface FeepolicyParamBeanMapper {
    int insert(FeepolicyParamBean record);

    int insertSelective(FeepolicyParamBean record);
}