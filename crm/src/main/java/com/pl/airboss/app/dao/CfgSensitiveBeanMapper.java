package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.CfgSensitiveBean;

import java.util.List;

public interface CfgSensitiveBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(CfgSensitiveBean record);

    int insertSelective(CfgSensitiveBean record);

    CfgSensitiveBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(CfgSensitiveBean record);

    int updateByPrimaryKey(CfgSensitiveBean record);

    List<CfgSensitiveBean> selectAll();

    List<CfgSensitiveBean> selectList(CfgSensitiveBean bean);
}