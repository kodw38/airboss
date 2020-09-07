package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.UserBillAcctBean;

import java.util.List;

public interface UserBillAcctBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(UserBillAcctBean record);

    int insertSelective(UserBillAcctBean record);

    UserBillAcctBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(UserBillAcctBean record);

    int updateByPrimaryKey(UserBillAcctBean record);

    List<UserBillAcctBean> list(UserBillAcctBean bean);
}