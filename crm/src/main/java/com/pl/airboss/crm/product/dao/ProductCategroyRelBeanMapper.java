package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ProductCategroyRelBean;

public interface ProductCategroyRelBeanMapper {
    int deleteByPrimaryKey(Long relId);

    int insert(ProductCategroyRelBean record);

    int insertSelective(ProductCategroyRelBean record);

    ProductCategroyRelBean selectByPrimaryKey(Long relId);

    int updateByPrimaryKeySelective(ProductCategroyRelBean record);

    int updateByPrimaryKey(ProductCategroyRelBean record);
}