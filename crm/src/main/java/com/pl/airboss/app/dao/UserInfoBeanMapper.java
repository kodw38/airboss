package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.UserInfoBean;

import java.util.List;

public interface UserInfoBeanMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfoBean record);

    int insertSelective(UserInfoBean record);

    UserInfoBean selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfoBean record);

    int updateByPrimaryKey(UserInfoBean record);

    List<UserInfoBean> selectList(UserInfoBean bean);
}