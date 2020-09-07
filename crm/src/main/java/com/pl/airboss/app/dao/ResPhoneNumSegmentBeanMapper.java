package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.ResPhoneNumSegmentBean;

import java.util.List;

public interface ResPhoneNumSegmentBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(ResPhoneNumSegmentBean record);

    int insertSelective(ResPhoneNumSegmentBean record);

    ResPhoneNumSegmentBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(ResPhoneNumSegmentBean record);

    int updateByPrimaryKey(ResPhoneNumSegmentBean record);

    List<ResPhoneNumSegmentBean> selectList(ResPhoneNumSegmentBean bean);
}