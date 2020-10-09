package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustGroupBean;
import com.pl.airboss.crm.cm.bean.UserMemberBean;
import com.pl.airboss.crm.cm.bean.UserMemberBeanKey;

public interface UserMemberBeanMapper {
    int deleteByPrimaryKey(UserMemberBeanKey key);

    int insert(UserMemberBean record);

    int insertSelective(UserMemberBean record);

    UserMemberBean selectByPrimaryKey(UserMemberBeanKey key);

    int updateByPrimaryKeySelective(UserMemberBean record);

    int updateByPrimaryKey(UserMemberBean record);

    CustGroupBean selectGroupCustByUserId(Long userId);
}