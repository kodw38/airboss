package com.pl.airboss.crm.ac.dao;

import com.pl.airboss.crm.ac.bean.WriteOffLogBean;
import com.pl.airboss.crm.ac.bean.WriteOffLogBeanKey;

public interface WriteOffLogBeanMapper {
    int deleteByPrimaryKey(WriteOffLogBeanKey key);

    int insert(WriteOffLogBean record);

    int insertSelective(WriteOffLogBean record);

    WriteOffLogBean selectByPrimaryKey(WriteOffLogBeanKey key);

    int updateByPrimaryKeySelective(WriteOffLogBean record);

    int updateByPrimaryKey(WriteOffLogBean record);
}