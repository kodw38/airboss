package com.pl.airboss.framework.dao;

import com.pl.airboss.framework.bean.CfgServerListBean;

import java.util.List;

public interface CfgServerListBeanMapper {
    int deleteByPrimaryKey(String address);

    int insert(CfgServerListBean record);

    int insertSelective(CfgServerListBean record);

    CfgServerListBean selectByPrimaryKey(String address);

    int updateByPrimaryKeySelective(CfgServerListBean record);

    int updateByPrimaryKey(CfgServerListBean record);

    List<CfgServerListBean> selectAll();
}