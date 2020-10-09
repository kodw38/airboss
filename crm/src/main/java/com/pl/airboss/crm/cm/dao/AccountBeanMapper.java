package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.AccountBean;
import com.pl.airboss.crm.cm.bean.AccountBeanKey;

import java.util.List;

public interface AccountBeanMapper {
    int deleteByPrimaryKey(AccountBeanKey key);

    int insert(AccountBean record);

    int insertSelective(AccountBean record);

    AccountBean selectByPrimaryKey(AccountBeanKey key);

    int updateByPrimaryKeySelective(AccountBean record);

    int updateByPrimaryKey(AccountBean record);

    List<AccountBean> selectByCustId(Long custId);
}