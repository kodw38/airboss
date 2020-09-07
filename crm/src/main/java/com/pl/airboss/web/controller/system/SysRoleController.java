package com.pl.airboss.web.controller.system;

import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.bean.SecRoleBean;
import com.pl.airboss.web.bean.SecRoleOPBean;
import com.pl.airboss.web.dao.SecOperatorBeanMapper;
import com.pl.airboss.web.dao.SecRoleBeanMapper;
import com.pl.airboss.web.service.ISysService;
import com.pl.airboss.web.utils.ExcelUtil;
import com.pl.airboss.web.utils.ShiroUtils;
import com.pl.airboss.web.utils.TableDataInfo;
import com.pl.airboss.web.dao.SecRoleOPBeanMapper;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 角色信息
 * 
 */
@Controller
@RequestMapping("/system/role")
public class SysRoleController extends BaseController
{
    private String prefix = "system/role";

    @Autowired
    private SecRoleBeanMapper roleService;

    @Autowired
    private SecRoleOPBeanMapper roleOPBeanMapper;

    @Autowired
    private SecOperatorBeanMapper userService;


    @Autowired
    @Qualifier("SysService")
    ISysService sysService;

    @RequiresPermissions("system:role:view")
    @GetMapping()
    public String role()
    {
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SecRoleBean role)
    {
        startPage();
        List<SecRoleBean> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:role:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SecRoleBean role)
    {
        List<SecRoleBean> list = roleService.selectRoleList(role);
        ExcelUtil<SecRoleBean> util = new ExcelUtil<SecRoleBean>(SecRoleBean.class);
        return util.exportExcel(list, "角色数据");
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    @RequiresPermissions("system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SecRoleBean role)
    {
        if (!sysService.checkRoleNameUnique(role.getRoleName()))
        {
            return error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (!sysService.checkRoleCodeUnique(role.getRoleCode()))
        {
            return error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setOpId(ShiroUtils.getUserId());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(sysService.insertRole(role));

    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        mmap.put("role", roleService.selectByPrimaryKey(roleId.intValue()));
        return prefix + "/edit";
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SecRoleBean role)
    {
        //roleService.checkRoleAllowed(role);
        /*if (!sysService.checkRoleNameUnique(role.getRoleName()))
        {
            return error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (!sysService.checkRoleCodeUnique(role.getRoleCode()))
        {
            return error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }*/
        role.setOpId(ShiroUtils.getUserId());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(sysService.updateRoleByPrimaryKey(role));
    }

    /**
     * 角色分配数据权限
     */
    @GetMapping("/authDataScope/{roleId}")
    public String authDataScope(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        mmap.put("role", roleService.selectByPrimaryKey(roleId.intValue()));
        return prefix + "/dataScope";
    }

    /**
     * 保存角色分配数据权限
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/authDataScope")
    @ResponseBody
    public AjaxResult authDataScopeSave(SecRoleBean role)
    {
        //roleService.checkRoleAllowed(role);
        role.setOpId(ShiroUtils.getUserId());
        /*if (roleService.authDataScope(role) > 0)
        {
            ShiroUtils.setSysUser(userService.selectUserById(ShiroUtils.getSysUser().getUserId()));
            return success();
        }*/
        return error();
    }

    @RequiresPermissions("system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String[] ids)
    {
        try
        {
            List ms = Arrays.asList(ids);
            return toAjax(sysService.deleteRolesByCode(ms));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 校验角色名称
     */
    @PostMapping("/checkRoleNameUnique")
    @ResponseBody
    public String checkRoleNameUnique(SecRoleBean role)
    {
        return String.valueOf(sysService.checkRoleNameUnique(role.getRoleName()));
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkRoleKeyUnique")
    @ResponseBody
    public String checkRoleKeyUnique(SecRoleBean role)
    {
        return String.valueOf(sysService.checkRoleCodeUnique(role.getRoleCode()));
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree()
    {
        return prefix + "/tree";
    }

    /**
     * 角色状态修改
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SecRoleBean role)
    {
        //roleService.checkRoleAllowed(role);
        return toAjax(roleService.changeStatus(role));
    }

    /**
     * 分配用户
     */
    @RequiresPermissions("system:role:edit")
    @GetMapping("/authUser/{roleId}")
    public String authUser(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        mmap.put("role", roleService.selectByPrimaryKey(roleId.intValue()));
        return prefix + "/authUser";
    }

    /**
     * 查询已分配用户角色列表
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/authUser/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(SecOperatorBean user)
    {
        startPage();
        List<SecOperatorBean> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }

    /**
     * 取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(SecRoleOPBean userRole)
    {
        return toAjax(roleOPBeanMapper.deleteRoleOpInfo(userRole));
    }

    /**
     * 批量取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    @ResponseBody
    public AjaxResult cancelAuthUserAll(Integer roleId, String userIds)
    {
        return toAjax(roleOPBeanMapper.deleteRoleOpInfos(roleId, userIds));
    }

    /**
     * 选择用户
     */
    @GetMapping("/authUser/selectUser/{roleCode}")
    public String selectUser(@PathVariable("roleCode") String roleCode, ModelMap mmap)
    {
        mmap.put("role", roleService.selectRoleByCode(roleCode));
        return prefix + "/selectUser";
    }

    /**
     * 查询未分配用户角色列表
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/authUser/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(SecOperatorBean user)
    {
        startPage();
        List<SecOperatorBean> list = userService.selectUnallocatedList(user);
        return getDataTable(list);
    }

    /**
     * 批量选择用户授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    @ResponseBody
    public AjaxResult selectAuthUserAll(String roleCode, String userIds)
    {
        return toAjax(sysService.insertAuthUsers(roleCode, userIds));
    }
}