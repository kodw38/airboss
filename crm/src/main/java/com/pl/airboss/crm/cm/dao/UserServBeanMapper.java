package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.UserServBean;

public interface UserServBeanMapper {
    int deleteByPrimaryKey(Long servInsId);

    int insert(UserServBean record);

    int insertSelective(UserServBean record);

    UserServBean selectByPrimaryKey(Long servInsId);

    int updateByPrimaryKeySelective(UserServBean record);

    int updateByPrimaryKey(UserServBean record);
}