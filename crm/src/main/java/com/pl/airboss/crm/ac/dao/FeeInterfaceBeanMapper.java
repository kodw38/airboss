package com.pl.airboss.crm.ac.dao;

import com.pl.airboss.crm.ac.bean.FeeInterfaceBean;

import java.util.List;

public interface FeeInterfaceBeanMapper {
    int deleteByPrimaryKey(Integer feepolicyId);

    int insert(FeeInterfaceBean record);

    int insertSelective(FeeInterfaceBean record);

    FeeInterfaceBean selectByPrimaryKey(Integer feepolicyId);

    int updateByPrimaryKeySelective(FeeInterfaceBean record);

    int updateByPrimaryKeyWithBLOBs(FeeInterfaceBean record);

    int updateByPrimaryKey(FeeInterfaceBean record);

    List<FeeInterfaceBean> select(FeeInterfaceBean cond);

    List<FeeInterfaceBean> selectList(FeeInterfaceBean cond);

    List<FeeInterfaceBean> selectByBundleId(Long bundleId);


}