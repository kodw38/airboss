package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ServiceBundleBean;

public interface ServiceBundleBeanMapper {
    int deleteByPrimaryKey(Integer servBundId);

    int insert(ServiceBundleBean record);

    int insertSelective(ServiceBundleBean record);

    ServiceBundleBean selectByPrimaryKey(Integer servBundId);

    int updateByPrimaryKeySelective(ServiceBundleBean record);

    int updateByPrimaryKey(ServiceBundleBean record);
}