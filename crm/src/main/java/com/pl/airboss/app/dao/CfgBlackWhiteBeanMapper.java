package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.CfgBlackWhiteBean;

import java.util.List;

public interface CfgBlackWhiteBeanMapper {
    int deleteByPrimaryKey(String billId);

    int insert(CfgBlackWhiteBean record);

    int insertSelective(CfgBlackWhiteBean record);

    CfgBlackWhiteBean selectByPrimaryKey(String billId);

    int updateByPrimaryKeySelective(CfgBlackWhiteBean record);

    int updateByPrimaryKey(CfgBlackWhiteBean record);

    List<CfgBlackWhiteBean> selectAll();

    List<CfgBlackWhiteBean> selectList(CfgBlackWhiteBean bean);
}