package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ServiceParamBean;

import java.util.List;

public interface ServiceParamBeanMapper {
    int deleteByPrimaryKey(Long paramId);

    int insert(ServiceParamBean record);

    int insertSelective(ServiceParamBean record);

    ServiceParamBean selectByPrimaryKey(Long paramId);

    int updateByPrimaryKeySelective(ServiceParamBean record);

    int updateByPrimaryKey(ServiceParamBean record);

    int insertList(List<ServiceParamBean> list);

    List<ServiceParamBean> selectByServiceId(Integer serviceId);
}