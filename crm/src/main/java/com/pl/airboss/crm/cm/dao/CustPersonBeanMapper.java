package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustPersonBean;

import java.util.List;

public interface CustPersonBeanMapper {
    int insert(CustPersonBean record);

    int insertSelective(CustPersonBean record);

    List<CustPersonBean> select(CustPersonBean record);
}