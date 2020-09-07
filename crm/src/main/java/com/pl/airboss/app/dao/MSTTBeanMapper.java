package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.MSTTBean;

import java.util.List;

public interface MSTTBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(MSTTBean record);

    int insertSelective(MSTTBean record);

    MSTTBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(MSTTBean record);

    int updateByPrimaryKey(MSTTBean record);

    List<MSTTBean> selectList(MSTTBean bean);

    int takeByPrimaryKey(Integer recId);
}