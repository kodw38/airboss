package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ProductCategoryBean;

import java.util.List;

public interface ProductCategoryBeanMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insert(ProductCategoryBean record);

    int insertSelective(ProductCategoryBean record);

    ProductCategoryBean selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(ProductCategoryBean record);

    int updateByPrimaryKey(ProductCategoryBean record);

    List<ProductCategoryBean> selectAll();
}