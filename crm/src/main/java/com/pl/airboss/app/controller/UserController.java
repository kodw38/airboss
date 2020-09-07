package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/14.
 */

import com.pl.airboss.app.bean.UserBillAcctBean;
import com.pl.airboss.app.bean.UserInfoBean;
import com.pl.airboss.app.dao.UserBillAcctBeanMapper;
import com.pl.airboss.app.dao.UserInfoBeanMapper;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.utils.DateUtils;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.ShiroUtils;
import com.pl.airboss.web.utils.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.pl.airboss.web.utils.AjaxResult.error;
import static com.pl.airboss.web.utils.AjaxResult.success;

/**
 * @ClassName UserController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/14 15:40
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/user")
public class UserController extends BaseController {
    private String prefix = "app/user";


    @Autowired
    UserInfoBeanMapper userInfoBeanMapper;

    @Autowired
    UserBillAcctBeanMapper billAcctBeanMapper;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    @RequiresPermissions("app:billid:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("app:billid:search")
    @PostMapping("/list")
    @ResponseBody
    public List<UserInfoBean> list(UserInfoBean bean){
        return userInfoBeanMapper.selectList(bean);
    }

    @PostMapping("/checkUnique")
    @ResponseBody
    public Boolean checkUnique(String billId){
        UserInfoBean b = new UserInfoBean();
        b.setBillId(billId);
        List<UserInfoBean> r = userInfoBeanMapper.selectList(b);
        if(null != r && r.size()>0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 新增
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @RequiresPermissions("app:billid:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated UserInfoBean bean)
    {
        List li =userInfoBeanMapper.selectList(bean);
        if (null != li && li.size()>0)
        {
            return error("用户'" + bean.getBillId() + "'失败，已存在");
        }
        bean.setCreateDate(new Date());
        bean.setOpId(ShiroUtils.getUserId());
        bean.setState(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(userInfoBeanMapper.insert(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Integer userId, ModelMap mmap)
    {
        UserInfoBean bean = userInfoBeanMapper.selectByPrimaryKey(userId);
        mmap.put("bean", bean);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:billid:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated UserInfoBean bean)
    {
        return toAjax(userInfoBeanMapper.updateByPrimaryKeySelective(bean));
    }

    /**
     * 删除
     */
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:billid:delete")
    @GetMapping("/remove/{userId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("userId") Integer userId)
    {

        return toAjax(userInfoBeanMapper.deleteByPrimaryKey(userId));
    }

    @RequiresPermissions("app:billacct:view")
    @GetMapping("/acct")
    public String acct(ModelMap map)
    {
        List<String> ls = DateUtils.getBeforeYearMonths(6);
        map.put("billCycle",ls);
        return prefix + "/acct";
    }

    @RequiresPermissions("app:billacct:view")
    @PostMapping("/acctList")
    @ResponseBody
    public TableDataInfo acctList(UserBillAcctBean bean){
        startPage();
        List<UserBillAcctBean> ls =  billAcctBeanMapper.list(bean);
        return getDataTable(ls);
    }
}
