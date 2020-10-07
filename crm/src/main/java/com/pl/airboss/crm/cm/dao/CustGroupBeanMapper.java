package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustGroupBeanWithBLOBs;

public interface CustGroupBeanMapper {
    int insert(CustGroupBeanWithBLOBs record);

    int insertSelective(CustGroupBeanWithBLOBs record);
}