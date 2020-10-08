package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.FeepolicyBean;

public interface FeepolicyBeanMapper {
    int insert(FeepolicyBean record);

    int insertSelective(FeepolicyBean record);
}