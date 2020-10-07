package com.pl.airboss.crm.ac.dao;

import com.pl.airboss.crm.ac.bean.AccessLogBean;
import com.pl.airboss.crm.ac.bean.AccessLogBeanKey;

public interface AccessLogBeanMapper {
    int deleteByPrimaryKey(AccessLogBeanKey key);

    int insert(AccessLogBean record);

    int insertSelective(AccessLogBean record);

    AccessLogBean selectByPrimaryKey(AccessLogBeanKey key);

    int updateByPrimaryKeySelective(AccessLogBean record);

    int updateByPrimaryKey(AccessLogBean record);
}