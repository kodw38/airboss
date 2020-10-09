package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.UserBean;

import java.util.List;

public interface UserBeanMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    UserBean selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    List<UserBean> selectUserListByCustId(Long custId);
}