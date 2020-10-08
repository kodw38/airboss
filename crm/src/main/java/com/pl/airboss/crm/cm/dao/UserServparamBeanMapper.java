package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.UserServparamBean;
import com.pl.airboss.crm.cm.bean.UserServparamBeanKey;

public interface UserServparamBeanMapper {
    int deleteByPrimaryKey(UserServparamBeanKey key);

    int insert(UserServparamBean record);

    int insertSelective(UserServparamBean record);

    UserServparamBean selectByPrimaryKey(UserServparamBeanKey key);

    int updateByPrimaryKeySelective(UserServparamBean record);

    int updateByPrimaryKey(UserServparamBean record);
}