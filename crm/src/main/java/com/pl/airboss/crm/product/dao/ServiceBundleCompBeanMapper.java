package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.ServiceBundleCompBean;
import com.pl.airboss.crm.product.bean.ServiceBundleCompBeanKey;

public interface ServiceBundleCompBeanMapper {
    int deleteByPrimaryKey(ServiceBundleCompBeanKey key);

    int insert(ServiceBundleCompBean record);

    int insertSelective(ServiceBundleCompBean record);

    ServiceBundleCompBean selectByPrimaryKey(ServiceBundleCompBeanKey key);

    int updateByPrimaryKeySelective(ServiceBundleCompBean record);

    int updateByPrimaryKey(ServiceBundleCompBean record);

    ServiceBundleCompBean selectByServiceId(Integer serviceId);
}