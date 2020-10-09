package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustGroupBean;
import com.pl.airboss.crm.cm.bean.CustGroupBeanWithBLOBs;

import java.util.List;

public interface CustGroupBeanMapper {
    int insert(CustGroupBeanWithBLOBs record);

    int insertSelective(CustGroupBeanWithBLOBs record);

    List<CustGroupBean> select(CustGroupBean record);
}