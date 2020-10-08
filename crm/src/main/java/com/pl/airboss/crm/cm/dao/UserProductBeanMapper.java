package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.UserProductBean;
import com.pl.airboss.crm.cm.bean.UserProductBeanKey;

public interface UserProductBeanMapper {
    int deleteByPrimaryKey(UserProductBeanKey key);

    int insert(UserProductBean record);

    int insertSelective(UserProductBean record);

    UserProductBean selectByPrimaryKey(UserProductBeanKey key);

    int updateByPrimaryKeySelective(UserProductBean record);

    int updateByPrimaryKey(UserProductBean record);
}