package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.AcctFeepolicyBean;
import com.pl.airboss.crm.cm.bean.AcctFeepolicyBeanKey;

public interface AcctFeepolicyBeanMapper {
    int deleteByPrimaryKey(AcctFeepolicyBeanKey key);

    int insert(AcctFeepolicyBean record);

    int insertSelective(AcctFeepolicyBean record);

    AcctFeepolicyBean selectByPrimaryKey(AcctFeepolicyBeanKey key);

    int updateByPrimaryKeySelective(AcctFeepolicyBean record);

    int updateByPrimaryKey(AcctFeepolicyBean record);
}