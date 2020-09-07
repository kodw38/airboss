package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.CfgMSTTBean;

import java.util.List;

public interface CfgMSTTBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(CfgMSTTBean record);

    int insertSelective(CfgMSTTBean record);

    CfgMSTTBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(CfgMSTTBean record);

    int updateByPrimaryKey(CfgMSTTBean record);

    List<CfgMSTTBean> selectAll();

    List<CfgMSTTBean> selectList(CfgMSTTBean bean);
}