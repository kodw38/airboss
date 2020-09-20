package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ServiceBean;

public interface ServiceBeanMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(ServiceBean record);

    int insertSelective(ServiceBean record);

    ServiceBean selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(ServiceBean record);

    int updateByPrimaryKey(ServiceBean record);
}