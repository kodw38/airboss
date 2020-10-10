package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustGroupBean;
import com.pl.airboss.crm.cm.bean.UserMemberBean;
import com.pl.airboss.crm.cm.bean.UserMemberBeanKey;

import java.util.List;

public interface UserMemberBeanMapper {
    int deleteByPrimaryKey(UserMemberBeanKey key);

    int insert(UserMemberBean record);

    int insertSelective(UserMemberBean record);

    UserMemberBean selectByPrimaryKey(UserMemberBeanKey key);

    int updateByPrimaryKeySelective(UserMemberBean record);

    int updateByPrimaryKey(UserMemberBean record);

    CustGroupBean selectGroupCustByUserId(Long userId);

    UserMemberBean selectByPhoneNumberAndCustId(String phoneNumber,Long custId);

    List<UserMemberBean> selectUserMemberList(Long custId);
}