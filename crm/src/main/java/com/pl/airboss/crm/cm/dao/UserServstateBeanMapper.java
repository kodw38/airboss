package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.UserServstateBean;
import com.pl.airboss.crm.cm.bean.UserServstateBeanKey;

public interface UserServstateBeanMapper {
    int deleteByPrimaryKey(UserServstateBeanKey key);

    int insert(UserServstateBean record);

    int insertSelective(UserServstateBean record);

    UserServstateBean selectByPrimaryKey(UserServstateBeanKey key);

    int updateByPrimaryKeySelective(UserServstateBean record);

    int updateByPrimaryKey(UserServstateBean record);
}