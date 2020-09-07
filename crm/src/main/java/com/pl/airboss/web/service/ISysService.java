package com.pl.airboss.web.service;

import com.pl.airboss.web.bean.SecFunctionBean;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.bean.SysDictData;
import com.pl.airboss.web.utils.Ztree;
import com.pl.airboss.web.bean.SecRoleBean;

import java.util.List;

/**
 *@Description
 *@auther Kod Wong
 *@Date 2020/4/27 15:10
 *@Param
 *@return
 *@Version 1.0
 */
public interface ISysService {
    //根据操作员编号获取菜单列表
    public List<SecFunctionBean> getMenuesByLoginCode(String loginCode);

    public String getRolesByOpId(Integer opId);

    public SecOperatorBean getOperatorById(Integer opId);

    public boolean resetUserPwd(SecOperatorBean op);

    public boolean updateUserInfo(SecOperatorBean op);


    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */

    public List<SecFunctionBean> selectMenuList(SecFunctionBean menu, Integer userId);

    public SecFunctionBean selectMenuById(Integer recId);

    public List<Ztree> menuTreeData(Integer userId);

    public List<Ztree> roleMenuTreeData(SecRoleBean role, Integer userId);

    public String checkMenuNameUnique(SecFunctionBean menu);

    public int updateMenu(SecFunctionBean bean);

    public int insertMenu(SecFunctionBean bean);

    public int deleteMenuById(Integer menuId);

    public int selectCountRoleMenuByMenuId(Integer menuId);

    int selectCountMenuByParentId(Integer menuId);

    public boolean checkRoleNameUnique(String roleNaem);
    public boolean checkRoleCodeUnique(String roleCode);

    public List<SysDictData> getType(String type);

    public List<SecRoleBean> getRoles();

    public int insertRole(SecRoleBean role);

    public int deleteRolesByCode(List<String> ids);

    public int updateRoleByPrimaryKey(SecRoleBean bean);

    public int insertAuthUsers(String roleCode, String userIds);

    public String importUser(List<SecOperatorBean> userList, Boolean isUpdateSupport, String operName);

    public int insertUserAuth(Integer userId, String[] roleCodes);

    public int insertUser(SecOperatorBean user);

    public int deleteUserByIds(String ids);
}
