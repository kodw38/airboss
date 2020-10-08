package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.AcctFeepolicyParamBean;
import com.pl.airboss.crm.cm.bean.AcctFeepolicyParamBeanKey;

public interface AcctFeepolicyParamBeanMapper {
    int deleteByPrimaryKey(AcctFeepolicyParamBeanKey key);

    int insert(AcctFeepolicyParamBean record);

    int insertSelective(AcctFeepolicyParamBean record);

    AcctFeepolicyParamBean selectByPrimaryKey(AcctFeepolicyParamBeanKey key);

    int updateByPrimaryKeySelective(AcctFeepolicyParamBean record);

    int updateByPrimaryKey(AcctFeepolicyParamBean record);
}