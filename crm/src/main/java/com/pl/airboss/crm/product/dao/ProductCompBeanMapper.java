package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ProductCompBean;
import com.pl.airboss.crm.product.bean.ProductCompBeanKey;
import org.apache.ibatis.annotations.Param;

public interface ProductCompBeanMapper {
    int deleteByPrimaryKey(ProductCompBeanKey key);

    int insert(ProductCompBean record);

    int insertSelective(ProductCompBean record);

    ProductCompBean selectByPrimaryKey(ProductCompBeanKey key);

    int updateByPrimaryKeySelective(ProductCompBean record);

    int updateByPrimaryKey(ProductCompBean record);

    ProductCompBean selectByProductIdAndServiceBundleId(@Param("productId") Integer prodId, @Param("servBundId") Integer serviceBundleId);
}