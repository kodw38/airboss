package com.pl.airboss.crm.ac.dao;

import com.pl.airboss.crm.ac.bean.AccountBalanceBean;
import com.pl.airboss.crm.ac.bean.AccountBalanceBeanKey;

public interface AccountBalanceBeanMapper {
    int deleteByPrimaryKey(AccountBalanceBeanKey key);

    int insert(AccountBalanceBean record);

    int insertSelective(AccountBalanceBean record);

    AccountBalanceBean selectByPrimaryKey(AccountBalanceBeanKey key);

    int updateByPrimaryKeySelective(AccountBalanceBean record);

    int updateByPrimaryKey(AccountBalanceBean record);
}