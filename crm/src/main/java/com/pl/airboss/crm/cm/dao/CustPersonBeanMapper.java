package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustPersonBean;

public interface CustPersonBeanMapper {
    int insert(CustPersonBean record);

    int insertSelective(CustPersonBean record);
}