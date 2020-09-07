package com.pl.airboss.web.dao;

import com.pl.airboss.web.bean.SecOrganizeBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecOrganizeBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(SecOrganizeBean record);

    int insertSelective(SecOrganizeBean record);

    SecOrganizeBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(SecOrganizeBean record);

    int updateByPrimaryKey(SecOrganizeBean record);

    List<SecOrganizeBean> selectOrganizeList(SecOrganizeBean bean);

    int selectOrganizeCount(SecOrganizeBean bean);

    int checkOrganizeExistUser(Long organizeId);

    List<SecOrganizeBean> selectChildrenOrganizeById(Long organzieId);

    int updateOrganizeChildren(List<SecOrganizeBean> organizeBeans);

    boolean checkOrganizeNameUnique(@Param("organizeName") String organizeName, @Param("parentId") long parentId);
}