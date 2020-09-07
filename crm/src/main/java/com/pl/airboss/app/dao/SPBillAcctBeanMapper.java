package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.SPBillAcctBean;

import java.util.List;

public interface SPBillAcctBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(SPBillAcctBean record);

    int insertSelective(SPBillAcctBean record);

    SPBillAcctBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(SPBillAcctBean record);

    int updateByPrimaryKey(SPBillAcctBean record);

    List<SPBillAcctBean> list(SPBillAcctBean bean);
}