package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ProductBean;
import com.pl.airboss.crm.product.bean.ProductBeanKey;
import com.pl.airboss.crm.product.bean.ProductInfo;
import com.pl.airboss.crm.product.bean.ServiceBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductBeanMapper {
    int deleteByPrimaryKey(Integer key);

    int insert(ProductBean record);

    int insertSelective(ProductBean record);

    ProductBean selectByPrimaryKey(Integer key);

    int updateByPrimaryKeySelective(ProductBean record);

    int updateByPrimaryKeyWithBLOBs(ProductBean record);

    int updateByPrimaryKey(ProductBean record);

    List<ProductInfo> selectProductInfo();

    List<ServiceBean> selectServices(@Param("prodId") Integer prodId);
}