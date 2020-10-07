package com.pl.airboss.crm.ac.dao;

import com.pl.airboss.crm.ac.bean.PayLogBean;
import com.pl.airboss.crm.ac.bean.PayLogBeanKey;

public interface PayLogBeanMapper {
    int deleteByPrimaryKey(PayLogBeanKey key);

    int insert(PayLogBean record);

    int insertSelective(PayLogBean record);

    PayLogBean selectByPrimaryKey(PayLogBeanKey key);

    int updateByPrimaryKeySelective(PayLogBean record);

    int updateByPrimaryKey(PayLogBean record);
}