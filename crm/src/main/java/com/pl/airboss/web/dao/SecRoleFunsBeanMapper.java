package com.pl.airboss.web.dao;

import com.pl.airboss.web.bean.SecRoleFunsBean;

import java.util.List;

public interface SecRoleFunsBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(SecRoleFunsBean record);

    int insertSelective(SecRoleFunsBean record);

    SecRoleFunsBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(SecRoleFunsBean record);

    int updateByPrimaryKey(SecRoleFunsBean record);

    int selectCountRoleMenuByMenuId(Integer menuId);

    int batchInsert(List<SecRoleFunsBean> records);
    int deleteByRoleCode(List<String> codes);
}