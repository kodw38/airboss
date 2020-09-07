package com.pl.airboss.app.dao;

import com.pl.airboss.app.bean.UserOrderBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserOrderBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(UserOrderBean record);

    int insertSelective(UserOrderBean record);

    UserOrderBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(UserOrderBean record);

    int updateByPrimaryKey(UserOrderBean record);

    List<UserOrderBean> selectList(UserOrderBean bean);

    int countCacheData();

    List<Map> selectCacheData(@Param("start") int start, @Param("len") int len);

}