package com.pl.airboss.crm.ac.dao;

import com.pl.airboss.crm.ac.bean.BillBean;

public interface BillBeanMapper {
    int insert(BillBean record);

    int insertSelective(BillBean record);
}