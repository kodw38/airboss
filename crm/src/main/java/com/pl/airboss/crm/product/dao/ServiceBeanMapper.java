package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ServiceBean;

import java.util.List;

public interface ServiceBeanMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(ServiceBean record);

    int insertSelective(ServiceBean record);

    ServiceBean selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(ServiceBean record);

    int updateByPrimaryKey(ServiceBean record);

    List<ServiceBean> select(ServiceBean cond);
}