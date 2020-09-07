package com.pl.airboss.web.dao;

import com.pl.airboss.web.bean.SecFunctionBean;
import com.pl.airboss.web.bean.SecFunctionBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecFunctionBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(SecFunctionBean record);

    int insertSelective(SecFunctionBean record);

    List<SecFunctionBean> selectByExample(SecFunctionBeanExample example);

    SecFunctionBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(SecFunctionBean record);

    int updateByPrimaryKey(SecFunctionBean record);

    List<SecFunctionBean> selectMenusByLoginCode(String loginCode);

    List<String> selectMenuPermsByOpId(Integer opId);

    List<SecFunctionBean> selectMenuList(SecFunctionBean bean);

    List<SecFunctionBean> selectMenuListByUserId(Integer userId);

    List<SecFunctionBean>  selectMenuAll();

    List<Integer> selectMenuTree(String roleCode);

    SecFunctionBean checkMenuNameUnique(@Param("funName")String funName, @Param("parentId")Integer parentId);

    int selectCountRoleMenuByMenuId(Integer menuId);

    int selectCountMenuByParentId(Integer menuId);


}