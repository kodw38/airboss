package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustomerBean;

import java.util.List;

public interface CustomerBeanMapper {
    int insert(CustomerBean record);

    int insertSelective(CustomerBean record);

    List<CustomerBean> select(CustomerBean record);

    int delete(Long custId);
}