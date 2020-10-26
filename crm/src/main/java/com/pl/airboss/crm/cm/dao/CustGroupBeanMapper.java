package com.pl.airboss.crm.cm.dao;

import com.pl.airboss.crm.cm.bean.CustGroupBean;
import com.pl.airboss.crm.cm.bean.CustGroupBeanWithBLOBs;
import com.pl.airboss.crm.cm.bean.CustomerBean;
import com.pl.airboss.crm.cm.bean.UserBean;

import java.util.List;

public interface CustGroupBeanMapper {
    int insert(CustGroupBeanWithBLOBs record);

    int insertBase(CustGroupBean record);

    int insertSelective(CustGroupBeanWithBLOBs record);

    List<CustGroupBean> select(CustGroupBean record);

    List<UserBean> selectUsers(Long custId);

    CustGroupBean selectByCustId(Long custId);
    int delete(Long custId);

    List<CustGroupBean> selectList(CustomerBean record);
}