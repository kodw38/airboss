package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.FeePolicyBean;
import com.pl.airboss.crm.product.bean.FeePolicyBeanKey;

public interface FeePolicyBeanMapper {
    int deleteByPrimaryKey(FeePolicyBeanKey key);

    int insert(FeePolicyBean record);

    int insertSelective(FeePolicyBean record);

    FeePolicyBean selectByPrimaryKey(FeePolicyBeanKey key);

    int updateByPrimaryKeySelective(FeePolicyBean record);

    int updateByPrimaryKeyWithBLOBs(FeePolicyBean record);
}