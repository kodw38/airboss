package com.pl.airboss.web.service.impl;/**
 * Created by admin on 2020/4/27.
 */

import com.pl.airboss.utils.Convert;
import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.bean.*;
import com.pl.airboss.web.config.ResourcesConfig;
import com.pl.airboss.web.dao.*;
import com.pl.airboss.web.cache.CacheStaticData;
import com.pl.airboss.framework.bean.BusinessException;
import com.pl.airboss.framework.cache.impl.CacheManager;
import com.pl.airboss.utils.Md5Utils;
import com.pl.airboss.web.service.ISysService;
import com.pl.airboss.web.utils.ShiroUtils;
import com.pl.airboss.web.utils.Ztree;
import com.pl.airboss.web.bean.*;
import com.pl.airboss.web.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName SysServiceImpl
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/4/27 15:12
 * @Version 1.0
 **/
@Service("SysService")
public class SysServiceImpl implements ISysService {
    transient static Logger log = LoggerFactory.getLogger(SysServiceImpl.class);

    @Autowired
    SecFunctionBeanMapper functionBeanMapper;

    @Autowired
    SecRoleOPBeanMapper roleOPBeanMapper;

    @Autowired
    SecRoleFunsBeanMapper roleFunsBeanMapper;

    @Autowired
    SecOperatorBeanMapper operatorBeanMapper;

    @Autowired
    SecRoleBeanMapper roleBeanMapper;

    @Autowired
    ResourcesConfig config;

    @Autowired
    CacheManager cacheManager;

    /**
     *@Description 主页面获取菜单列表
     *@auther Kod Wong
     *@Date 2020/5/2 10:54
     *@Param
     *@return
     *@Version 1.0
     */
    @Override
    public List<SecFunctionBean> getMenuesByLoginCode(String loginCode) {
        List<SecFunctionBean> menus= functionBeanMapper.selectMenusByLoginCode(loginCode);
        if(null != menus){
            /*for(SecFunctionBean m:menus){
                m.setTarget("");
            }*/
        }
        return getChildPerms(menus, 0);
    }

    public List<SecFunctionBean> getChildPerms(List<SecFunctionBean> list, int parentId)
    {
        List<SecFunctionBean> returnList = new ArrayList<SecFunctionBean>();
        for (Iterator<SecFunctionBean> iterator = list.iterator(); iterator.hasNext();)
        {
            SecFunctionBean t = (SecFunctionBean) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    private void recursionFn(List<SecFunctionBean> list, SecFunctionBean t)
    {
        // 得到子节点列表
        List<SecFunctionBean> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SecFunctionBean tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<SecFunctionBean> it = childList.iterator();
                while (it.hasNext())
                {
                    SecFunctionBean n = (SecFunctionBean) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<SecFunctionBean> getChildList(List<SecFunctionBean> list, SecFunctionBean t)
    {
        List<SecFunctionBean> tlist = new ArrayList<SecFunctionBean>();
        Iterator<SecFunctionBean> it = list.iterator();
        while (it.hasNext())
        {
            SecFunctionBean n = (SecFunctionBean) it.next();
            if (n.getParentId().longValue() == t.getRecId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SecFunctionBean> list, SecFunctionBean t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }


    /**
     * 查询用户所属角色组
     *
     * @param opId 用户ID
     * @return 结果
     */
    @Override
    public String getRolesByOpId(Integer opId)
    {
        List<String> list = roleOPBeanMapper.selectRoleCodesByOpId(opId.intValue());
        StringBuffer idsStr = new StringBuffer();
        for (String role : list)
        {
            idsStr.append(role).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    public SecOperatorBean getOperatorById(Integer opId){
        return operatorBeanMapper.selectByPrimaryKey(opId);
    }


    public boolean resetUserPwd(SecOperatorBean op){
        int n =operatorBeanMapper.updateByPrimaryKeySelective(op);
        return n>0;
    }

    @Transactional
    public boolean updateUserInfo(SecOperatorBean op){
        int n =operatorBeanMapper.updateByPrimaryKeySelective(op);
        if(null!=op.getRoleIds() && op.getRoleIds().size()>0){
            List<SecRoleOPBean> ls = new ArrayList<>();
            roleOPBeanMapper.deleteByUserId(op.getRecId());
            for(String code:op.getRoleIds()){
                SecRoleOPBean b = new SecRoleOPBean();
                b.setOpId(ShiroUtils.getUserId());
                b.setState(1);
                b.setCreateDate(new Date());
                b.setRoleCode(code);
                b.setOperatorId(op.getRecId());
                ls.add(b);
            }
            if(ls.size()>0)
                roleOPBeanMapper.batchInsert(ls);
        }
        return n>0;
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<SecFunctionBean> selectMenuList(SecFunctionBean menu, Integer userId)
    {
        List<SecFunctionBean> menuList = null;
        if (ShiroUtils.getSysUser().getIsAdmin()==1)
        {
            menuList = functionBeanMapper.selectMenuList(menu);
        }
        else
        {
            menuList = functionBeanMapper.selectMenuListByUserId(menu.getRecId());
        }
        return menuList;
    }

    public SecFunctionBean selectMenuById(Integer recId){
        return functionBeanMapper.selectByPrimaryKey(recId);
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    public List<Ztree> menuTreeData(Integer userId)
    {
        List<SecFunctionBean> menuList = selectMenuAll(userId.intValue());
        List<Ztree> ztrees = initZtree(menuList);
        return ztrees;
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    public List<SecFunctionBean> selectMenuAll(Integer userId)
    {
        List<SecFunctionBean> menuList = null;
        if (ShiroUtils.getSysUser().getIsAdmin()==1)
        {
            menuList = functionBeanMapper.selectMenuAll();
        }
        else
        {
            menuList = functionBeanMapper.selectMenuListByUserId(userId);
        }
        return menuList;
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SecFunctionBean> menuList)
    {
        return initZtree(menuList, null, false);
    }
    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SecFunctionBean> menuList, List<Integer> roleMenuList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleMenuList);
        for (SecFunctionBean menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getRecId().longValue());
            ztree.setpId(menu.getParentId().longValue());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getFunName());
            if (isCheck)
            {
                ztree.setChecked(roleMenuList.contains(menu.getRecId())); //+menu.getPerms()
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(SecFunctionBean menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getFunName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Ztree> roleMenuTreeData(SecRoleBean role, Integer userId)
    {
        Integer roleId = role.getRecId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SecFunctionBean> menuList = selectMenuAll(userId.intValue());
        if (StringUtils.isNotNull(roleId))
        {
            List<Integer> roleMenuList = functionBeanMapper.selectMenuTree(role.getRoleCode());
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else
        {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }

    @Override
    public String checkMenuNameUnique(SecFunctionBean menu)
    {
        Long menuId = StringUtils.isNull(menu.getRecId()) ? -1L : menu.getRecId();
        SecFunctionBean info = functionBeanMapper.checkMenuNameUnique(menu.getFunName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getRecId().longValue() != menuId.longValue())
        {
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }

    public int updateMenu(SecFunctionBean bean){
        bean.setDoneDate(new Date());
        bean.setOpId(ShiroUtils.getUserId());
        return functionBeanMapper.updateByPrimaryKey(bean);
    }

    public int insertMenu(SecFunctionBean bean){
        return functionBeanMapper.insert(bean);
    }

    public int deleteMenuById(Integer menuId){
        return functionBeanMapper.deleteByPrimaryKey(menuId);
    }

    public int selectCountRoleMenuByMenuId(Integer menuId){
        return roleFunsBeanMapper.selectCountRoleMenuByMenuId(menuId);
    }

    public int selectCountMenuByParentId(Integer menuId){
        return functionBeanMapper.selectCountMenuByParentId(menuId);
    }

    public boolean checkRoleNameUnique(String roleName){
        return !roleBeanMapper.getRoleByRoleName(roleName);
    }

    public boolean checkRoleCodeUnique(String roleCode){
        return  !roleBeanMapper.getRoleByRoleCode(roleCode);
    }

    public List<SysDictData> getType(String type){
        return (List<SysDictData>)cacheManager.getMapCacheValue(CacheStaticData.class.getName(),type);
    }

    public String getLabel(String type,String value){
        List<SysDictData> all = (List<SysDictData>)cacheManager.getMapCacheValue(CacheStaticData.class.getName(),type);
        if(null != all) {
            for (SysDictData d : all) {
                if(d.getDictValue().equalsIgnoreCase(value)){
                    return d.getDictLabel();
                }
            }
        }
        return null;
    }

    public List<SecRoleBean> getRoles(){
        return roleBeanMapper.getRoleList(null);
    }

    @Transactional
    public int insertRole(SecRoleBean role){
        roleBeanMapper.insert(role);
        List ms = role.getMenuIds();
        if(null != ms) {
            List<SecRoleFunsBean> bs = new ArrayList<>();
            for(Object m:ms) {
                SecRoleFunsBean b = new SecRoleFunsBean();
                b.setCreateDate(new Date());
                b.setFunId(Integer.valueOf(m.toString()));
                b.setRoleCode(role.getRoleCode());
                b.setContainsChildren(1);
                b.setDoneDate(new Date());
                b.setOpId(ShiroUtils.getUserId());
                b.setState(1);
                bs.add(b);
            }
            return roleFunsBeanMapper.batchInsert(bs);
        }
        return 1;
    }

    @Transactional
    public int updateRoleByPrimaryKey(SecRoleBean bean){
        bean.setDoneDate(new Date());
        bean.setOpId(ShiroUtils.getUserId());
        roleBeanMapper.updateByPrimaryKeySelective(bean);
        List ms = bean.getMenuIds();
        if(null != ms) {
            List li = new ArrayList();
            li.add(bean.getRoleCode());
            roleFunsBeanMapper.deleteByRoleCode(li);
            List<SecRoleFunsBean> bs = new ArrayList<>();
            for(Object m:ms) {
                SecRoleFunsBean b = new SecRoleFunsBean();
                b.setCreateDate(new Date());
                b.setFunId(Integer.valueOf(m.toString()));
                b.setRoleCode(bean.getRoleCode());
                b.setContainsChildren(1);
                b.setDoneDate(new Date());
                b.setOpId(ShiroUtils.getUserId());
                b.setState(1);
                bs.add(b);
            }
            return roleFunsBeanMapper.batchInsert(bs);
        }
        return 1;
    }
    public int insertAuthUsers(String roleCode, String userIds)
    {
        Long[] users = Convert.toLongArray(userIds);
        // 新增用户与角色管理
        List<SecRoleOPBean> list = new ArrayList<SecRoleOPBean>();
        for (Long userId : users)
        {
            SecRoleOPBean ur = new SecRoleOPBean();
            ur.setOperatorId(userId.intValue());
            ur.setRoleCode(roleCode);
            ur.setCreateDate(new Date());
            ur.setState(1);
            ur.setOpId(ShiroUtils.getUserId());
            list.add(ur);
        }
        return roleOPBeanMapper.batchInsert(list);
    }
    @Transactional
    public int insertUserAuth(Integer userId, String[] roleCodes)
    {
        // 新增用户与角色管理
        List<SecRoleOPBean> list = new ArrayList<SecRoleOPBean>();
        for (String roleCode : roleCodes)
        {
            SecRoleOPBean ur = new SecRoleOPBean();
            ur.setOperatorId(userId);
            ur.setRoleCode(roleCode);
            ur.setCreateDate(new Date());
            ur.setState(1);
            ur.setOpId(ShiroUtils.getUserId());
            list.add(ur);
        }
        if(list.size()>0) {
            roleOPBeanMapper.deleteByUserId(userId);
            return roleOPBeanMapper.batchInsert(list);
        }
        return 1;
    }
    @Transactional
    public int deleteRolesByCode(List<String> ids){
        roleBeanMapper.deleteByCodes(ids);
        roleFunsBeanMapper.deleteByRoleCode(ids);
        return 1;
    }
    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    public void checkRoleAllowed(SecRoleBean role)
    {
        if (StringUtils.isNotNull(role.getRoleType()) && role.getRoleType().equalsIgnoreCase("1"))
        {
            throw new BusinessException("不允许操作超级管理员角色");
        }
    }

    public String importUser(List<SecOperatorBean> userList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = config.getInitUserPassowrd();
        for (SecOperatorBean user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                SecOperatorBean u = operatorBeanMapper.selectUserByLoginCode(user.getLoginCode());
                if (StringUtils.isNull(u))
                {
                    user.setLoginPwd(Md5Utils.hash(user.getLoginCode() + password));
                    user.setOpId(ShiroUtils.getUserId());
                    operatorBeanMapper.insert(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginCode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    user.setOpId(ShiroUtils.getUserId());
                    operatorBeanMapper.updateByPrimaryKey(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getLoginCode() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getLoginCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getLoginCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Transactional
    public int insertUser(SecOperatorBean user){
        operatorBeanMapper.insert(user);
        List<String> roleids = user.getRoleIds();
        if(null!= roleids && roleids.size()>0){
            List<SecRoleOPBean> ls = new ArrayList<>();
            for(String code:roleids){
                SecRoleOPBean b = new SecRoleOPBean();
                b.setOpId(ShiroUtils.getUserId());
                b.setState(1);
                b.setCreateDate(new Date());
                b.setRoleCode(code);
                b.setOperatorId(user.getRecId());
                ls.add(b);
            }
            if(ls.size()>0)
            roleOPBeanMapper.batchInsert(ls);
        }
        return 1;

    }

    @Transactional
    public int deleteUserByIds(String ids){
        Long[] userIds = Convert.toLongArray(ids);

        operatorBeanMapper.deleteUserByIds(userIds);

        roleOPBeanMapper.deleteByUserIds(userIds);
        return 1;
    }
}
