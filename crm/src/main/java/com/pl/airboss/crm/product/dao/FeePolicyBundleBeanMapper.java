package com.pl.airboss.crm.product.dao;

import com.pl.airboss.crm.product.bean.FeePolicyBundleBean;

import java.util.List;

public interface FeePolicyBundleBeanMapper {
    int deleteByPrimaryKey(Integer feepolicyBundId);

    int insert(FeePolicyBundleBean record);

    int insertSelective(FeePolicyBundleBean record);

    FeePolicyBundleBean selectByPrimaryKey(Integer feepolicyBundId);

    int updateByPrimaryKeySelective(FeePolicyBundleBean record);

    int updateByPrimaryKey(FeePolicyBundleBean record);

    List<FeePolicyBundleBean> selectList(FeePolicyBundleBean cond);
}