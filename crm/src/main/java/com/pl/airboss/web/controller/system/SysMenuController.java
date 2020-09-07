package com.pl.airboss.web.controller.system;

import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.web.utils.Ztree;
import com.pl.airboss.web.bean.SecFunctionBean;
import com.pl.airboss.web.bean.SecRoleBean;
import com.pl.airboss.web.bean.UserConstants;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.service.ISysService;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 菜单信息
 * 
 */
@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController
{
    private String prefix = "system/menu";

    @Autowired
    @Qualifier("SysService")
    private ISysService sysService;

    @RequiresPermissions("system:menu:view")
    @GetMapping()
    public String menu()
    {
        return prefix + "/menu";
    }

    @RequiresPermissions("system:menu:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SecFunctionBean> list(SecFunctionBean menu)
    {
        Integer userId = ShiroUtils.getUserId();
        List<SecFunctionBean> menuList = sysService.selectMenuList(menu, userId);
        return menuList;
    }

    /**
     * 删除菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:menu:remove")
    @GetMapping("/remove/{menuId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("menuId") Long menuId)
    {
        if (sysService.selectCountMenuByParentId(menuId.intValue()) > 0)
        {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
        if (sysService.selectCountRoleMenuByMenuId(menuId.intValue()) > 0)
        {
            return AjaxResult.warn("菜单已分配,不允许删除");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(sysService.deleteMenuById(menuId.intValue()));
    }

    /**
     * 新增
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        SecFunctionBean menu = null;
        if (0L != parentId)
        {
            menu = sysService.selectMenuById(parentId.intValue());
        }
        else
        {
            menu = new SecFunctionBean();
            menu.setRecId(0);
            menu.setFunName("主目录");
        }
        mmap.put("menu", menu);
        return prefix + "/add";
    }

    /**
     * 新增保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:menu:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SecFunctionBean menu)
    {
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(sysService.checkMenuNameUnique(menu)))
        {
            return error("新增菜单'" + menu.getFunName() + "'失败，菜单名称已存在");
        }
        menu.setState(1);
        menu.setFunClass("MENU");
        menu.setCreateDate(new Date());
        menu.setDoneDate(new Date());
        menu.setOpId(ShiroUtils.getUserId());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(sysService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Long menuId, ModelMap mmap)
    {
        SecFunctionBean bean = sysService.selectMenuById(menuId.intValue());
        if(bean.getParentId()>0) {
            SecFunctionBean p = sysService.selectMenuById(bean.getParentId());
            if(null != p)
            bean.setParentName(p.getFunName());
        }
        mmap.put("menu", bean);
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:menu:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SecFunctionBean menu)
    {
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(sysService.checkMenuNameUnique(menu)))
        {
            return error("修改菜单'" + menu.getFunName() + "'失败，菜单名称已存在");
        }
        menu.setOpId(ShiroUtils.getUserId());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(sysService.updateMenu(menu));
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon()
    {
        return prefix + "/icon";
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(SecFunctionBean menu)
    {
        return sysService.checkMenuNameUnique(menu);
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Ztree> roleMenuTreeData(SecRoleBean role)
    {
        Integer userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = sysService.roleMenuTreeData(role, userId);
        return ztrees;
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Ztree> menuTreeData()
    {
        Integer userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = sysService.menuTreeData(userId);
        return ztrees;
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap)
    {
        mmap.put("menu", sysService.selectMenuById(menuId.intValue()));
        return prefix + "/tree";
    }
}