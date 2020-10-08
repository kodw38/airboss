package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.AccountUserBean;
import com.pl.airboss.crm.cm.bean.AccountUserBeanKey;

public interface AccountUserBeanMapper {
    int deleteByPrimaryKey(AccountUserBeanKey key);

    int insert(AccountUserBean record);

    int insertSelective(AccountUserBean record);

    AccountUserBean selectByPrimaryKey(AccountUserBeanKey key);

    int updateByPrimaryKeySelective(AccountUserBean record);

    int updateByPrimaryKey(AccountUserBean record);
}