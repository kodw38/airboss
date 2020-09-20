package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ProductParamBean;

public interface ProductParamBeanMapper {
    int deleteByPrimaryKey(Long paramId);

    int insert(ProductParamBean record);

    int insertSelective(ProductParamBean record);

    ProductParamBean selectByPrimaryKey(Long paramId);

    int updateByPrimaryKeySelective(ProductParamBean record);

    int updateByPrimaryKey(ProductParamBean record);
}