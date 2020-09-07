package com.pl.airboss.web.dao;

import com.pl.airboss.web.bean.SecRoleBean;

import java.util.List;

public interface SecRoleBeanMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SecRoleBean record);

    int insertSelective(SecRoleBean record);

    SecRoleBean selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SecRoleBean record);
    int changeStatus(SecRoleBean record);

    int updateByPrimaryKey(SecRoleBean record);

    List<SecRoleBean> selectRoleList(SecRoleBean bean);

    List<SecRoleBean> getRoleList(SecRoleBean bean);

    Boolean getRoleByRoleName(String roleName);
    Boolean getRoleByRoleCode(String roleCode);

    int deleteByCodes(List<String> codes);
    SecRoleBean selectRoleByCode(String roleId);

    List<SecRoleBean> selectRolesByUserId(Integer opId);
}