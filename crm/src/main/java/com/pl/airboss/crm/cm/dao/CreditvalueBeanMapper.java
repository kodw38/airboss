package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CreditvalueBean;

public interface CreditvalueBeanMapper {
    int insert(CreditvalueBean record);

    int insertSelective(CreditvalueBean record);
}