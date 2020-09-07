package com.pl.airboss.web.controller.system;

import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.web.Constants;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.web.dao.SecRoleBeanMapper;
import com.pl.airboss.web.utils.ExcelUtil;
import com.pl.airboss.web.bean.SecOperatorBean;
import com.pl.airboss.web.bean.SecRoleBean;
import com.pl.airboss.web.bean.SecRoleOPBean;
import com.pl.airboss.web.dao.SecOperatorBeanMapper;
import com.pl.airboss.web.dao.SecRoleOPBeanMapper;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.service.ISysService;
import com.pl.airboss.web.service.SysPasswordService;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.ShiroUtils;
import com.pl.airboss.web.utils.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 用户信息
 * 
 */
@Controller
@RequestMapping("/system/user")
public class SysOperatorController extends BaseController
{
    private String prefix = "system/user";

    @Autowired
    private SecOperatorBeanMapper userService;

    @Autowired
    private SecRoleBeanMapper roleService;

    @Autowired
    SecRoleOPBeanMapper roleOPBeanMapper;

    @Autowired
    ISysService sysService;
    //@Autowired
    //private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SecOperatorBean user)
    {
        startPage();
        List<SecOperatorBean> list = userService.selectList(user);
        return getDataTable(list);
    }

    @RequiresPermissions("system:user:list")
    @PostMapping("/listAvailable")
    @ResponseBody
    public TableDataInfo listAvailable(SecOperatorBean user)
    {
        startPage();
        List<SecOperatorBean> list = userService.selectAvailableList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SecOperatorBean user)
    {
        List<SecOperatorBean> list = userService.selectList(user);
        ExcelUtil<SecOperatorBean> util = new ExcelUtil<SecOperatorBean>(SecOperatorBean.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SecOperatorBean> util = new ExcelUtil<SecOperatorBean>(SecOperatorBean.class);
        List<SecOperatorBean> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginCode();
        String message = sysService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SecOperatorBean> util = new ExcelUtil<SecOperatorBean>(SecOperatorBean.class);
        return util.importTemplateExcel("操作员数据");
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("roles", roleService.selectRoleList(new SecRoleBean()));
        //mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SecOperatorBean user)
    {
        if (userService.checkLoginCodeExist(user.getLoginCode()))
        {
            return error("新增用户'" + user.getLoginCode() + "'失败，登录账号已存在");
        }
        else if (userService.checkPhoneNumExist(user.getPhoneNum(),null))
        {
            return error("新增用户'" + user.getLoginCode() + "'失败，手机号码已存在");
        }
        else if (userService.checkEmailExist(user.getEmail(),null))
        {
            return error("新增用户'" + user.getLoginCode() + "'失败，邮箱账号已存在");
        }
        user.setLoginPwd(passwordService.encryptPassword(user.getLoginCode(), user.getLoginPwd(), Constants.SALT));
        user.setOpId(ShiroUtils.getUserId());
        return toAjax(sysService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Integer userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectWithOrgNameByOpId(userId));
        mmap.put("roles", roleService.selectRolesByUserId(userId));
        //mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SecOperatorBean user)
    {
        //userService.checkUserAllowed(user);
        /*if (userService.checkPhoneNumExist(user.getPhoneNum()))
        {
            return error("修改用户'" + user.getLoginCode() + "'失败，手机号码已存在");
        }
        else if (userService.checkEmailExist(user.getEmail()))
        {
            return error("修改用户'" + user.getLoginCode() + "'失败，邮箱账号已存在");
        }*/
        user.setOpId(ShiroUtils.getUserId());
        user.setDoneDate(new Date());
        return toAjax(sysService.updateUserInfo(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Integer userId, ModelMap mmap)
    {
        mmap.put("user", userService.selectByPrimaryKey(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SecOperatorBean user)
    {
        //userService.checkUserAllowed(user);
        //user.setSalt(ShiroUtils.randomSalt());
        user.setLoginPwd(passwordService.encryptPassword(user.getLoginCode(), user.getLoginPwd(), Constants.SALT));
        if (userService.updateByPrimaryKeySelective(user) > 0)
        {
            if (ShiroUtils.getUserId() == user.getRecId())
            {
                ShiroUtils.setSysUser(userService.selectByPrimaryKey(user.getRecId()));
            }
            return success();
        }
        return error();
    }

    /**
     * 进入授权角色页
     */
    @GetMapping("/authRole/{userId}")
    public String authRole(@PathVariable("userId") Integer userId, ModelMap mmap)
    {
        SecOperatorBean user = userService.selectByPrimaryKey(userId);
        // 获取用户所属的角色列表
        List<SecRoleOPBean> userRoles = roleOPBeanMapper.selectRoleOpByOperatorId(userId);
        mmap.put("user", user);
        mmap.put("userRoles", userRoles);
        return prefix + "/authRole";
    }

    /**
     * 用户授权角色
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/authRole/insertAuthRole")
    @ResponseBody
    public AjaxResult insertAuthRole(Integer userId, String[] roleCodes)
    {
        sysService.insertUserAuth(userId,roleCodes);
        return success();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(sysService.deleteUserByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public Boolean checkLoginNameUnique(SecOperatorBean user)
    {
        return userService.checkLoginNameUnique(user.getOpName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public Boolean checkPhoneUnique(SecOperatorBean user)
    {
        return !userService.checkPhoneNumExist(user.getPhoneNum(),user.getRecId());
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public Boolean checkEmailUnique(SecOperatorBean user)
    {
        return !userService.checkEmailExist(user.getEmail(),user.getRecId());
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SecOperatorBean user)
    {
        //userService.checkUserAllowed(user);
        return toAjax(userService.changeStatus(user));
    }
}