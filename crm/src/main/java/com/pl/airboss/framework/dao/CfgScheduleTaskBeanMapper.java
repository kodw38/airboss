package com.pl.airboss.framework.dao;

import com.pl.airboss.framework.bean.CfgScheduleTaskBean;

import java.util.List;

public interface CfgScheduleTaskBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(CfgScheduleTaskBean record);

    int insertSelective(CfgScheduleTaskBean record);

    CfgScheduleTaskBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(CfgScheduleTaskBean record);

    int updateByPrimaryKey(CfgScheduleTaskBean record);

    List<CfgScheduleTaskBean> selectAll();
    List<CfgScheduleTaskBean> selectList(CfgScheduleTaskBean bean);

}