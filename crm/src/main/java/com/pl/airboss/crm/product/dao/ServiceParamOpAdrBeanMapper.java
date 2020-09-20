package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ServiceParamOpAdrBean;

public interface ServiceParamOpAdrBeanMapper {
    int deleteByPrimaryKey(Long adrId);

    int insert(ServiceParamOpAdrBean record);

    int insertSelective(ServiceParamOpAdrBean record);

    ServiceParamOpAdrBean selectByPrimaryKey(Long adrId);

    int updateByPrimaryKeySelective(ServiceParamOpAdrBean record);

    int updateByPrimaryKey(ServiceParamOpAdrBean record);
}