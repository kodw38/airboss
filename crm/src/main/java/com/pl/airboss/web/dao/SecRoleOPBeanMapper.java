package com.pl.airboss.web.dao;

import com.pl.airboss.web.bean.SecRoleOPBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecRoleOPBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(SecRoleOPBean record);

    int insertSelective(SecRoleOPBean record);

    SecRoleOPBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(SecRoleOPBean record);

    int updateByPrimaryKey(SecRoleOPBean record);

    List<String> selectRoleCodesByOpId(int opId);

    int deleteRoleOpInfo(SecRoleOPBean bean);

    int deleteRoleOpInfos(@Param("roleId") Integer roleId, @Param("userIds") String userIds);
    int batchInsert(List<SecRoleOPBean> list);

    List<SecRoleOPBean> selectRoleOpByOperatorId(Integer operatorId);
    int deleteByUserId(Integer operatorId);

    int deleteByUserIds(Long[] ids);
}