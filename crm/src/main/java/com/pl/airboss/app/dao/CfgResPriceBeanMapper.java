package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.CfgResPriceBean;

import java.util.List;

public interface CfgResPriceBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(CfgResPriceBean record);

    int insertSelective(CfgResPriceBean record);

    CfgResPriceBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(CfgResPriceBean record);

    int updateByPrimaryKey(CfgResPriceBean record);

    List<CfgResPriceBean> selectAll();

    List<CfgResPriceBean> selectList(CfgResPriceBean bean);
}