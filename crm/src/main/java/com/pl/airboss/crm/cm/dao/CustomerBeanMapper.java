package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustomerBean;

public interface CustomerBeanMapper {
    int insert(CustomerBean record);

    int insertSelective(CustomerBean record);
}