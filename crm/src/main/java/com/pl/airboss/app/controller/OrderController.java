package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/14.
 */

import com.pl.airboss.app.bean.UserInfoBean;
import com.pl.airboss.app.bean.UserOrderBean;
import com.pl.airboss.app.dao.UserInfoBeanMapper;
import com.pl.airboss.app.dao.UserOrderBeanMapper;
import com.pl.airboss.app.task.RefreshUserToRedis;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
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

import static com.pl.airboss.web.utils.AjaxResult.error;
import static com.pl.airboss.web.utils.AjaxResult.success;

/**
 * @ClassName OpenController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/14 15:51
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/order")
public class OrderController {
    private String prefix = "app/order";

    @Qualifier("com.pl.airboss.app.task.RefreshUserToRedis")
    RefreshUserToRedis userRedis;

    @Autowired
    UserOrderBeanMapper orderBeanMapper;

    @Autowired
    UserInfoBeanMapper userInfoBeanMapper;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    @RequiresPermissions("app:register:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/order";
    }

    @RequiresPermissions("app:register:view")
    @PostMapping("/list")
    @ResponseBody
    public List<UserOrderBean> list(UserOrderBean bean){
        return orderBeanMapper.selectList(bean);
    }

    @GetMapping("/checkUnique")
    @ResponseBody
    public AjaxResult checkUnique(String billId,String business){
        UserInfoBean i = new UserInfoBean();
        i.setBillId(billId);
        List ls = userInfoBeanMapper.selectList(i);
        if(null == ls || ls.size()==0){
            return error(billId +"还没有注册,请先注册再订购");
        }
        UserOrderBean b = new UserOrderBean();
        b.setBusiness(business);
        b.setBillId(billId);
        List li = orderBeanMapper.selectList(b);
        if(null != li && li.size()>0){
            return error(billId +"已经订购"+business+",不能重复订购");
        }else{
            return success();
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
    @RequiresPermissions("app:register:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated UserOrderBean bean)
    {
        UserInfoBean i = new UserInfoBean();
        i.setBillId(bean.getBillId());
        List ls = userInfoBeanMapper.selectList(i);
        if(null == ls || ls.size()==0){
            return error(bean.getBillId() +"还没有注册,请先注册再订购");
        }

        List li =orderBeanMapper.selectList(bean);
        if (null != li && li.size()>0)
        {
            return error("订购'" + bean.getBillId() + "'失败，已存在");
        }
        bean.setCreateDate(new Date());
        bean.setOpId(ShiroUtils.getUserId());
        bean.setState(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        int n = orderBeanMapper.insert(bean);
        if(n>0){
            setCentreCache(bean);
        }
        return toAjax(n);

    }

    //把新订购的用户信息放入redis中
    void setCentreCache(UserOrderBean bean){
        UserInfoBean user = userInfoBeanMapper.selectByPrimaryKey(bean.getUserId());
        if(null != user) {
            userRedis.add(user,bean);
        }
    }
    void removeCentreCache(String sp,String busi,String billId){
        userRedis.delete(sp,busi,billId);
    }


    /**
     * 修改
     */
    @GetMapping("/edit/{recId}")
    public String edit(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        UserOrderBean bean = orderBeanMapper.selectByPrimaryKey(recId);
        mmap.put("bean", bean);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "订购", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:register:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated UserOrderBean bean)
    {
        UserInfoBean i = new UserInfoBean();
        i.setBillId(bean.getBillId());
        List ls = userInfoBeanMapper.selectList(i);
        if(null == ls || ls.size()==0){
            return error(bean.getBillId() +"还没有注册,请先注册再订购");
        }
        List li =orderBeanMapper.selectList(bean);
        if (null != li && li.size()>0)
        {
            return error("订购'" + bean.getBillId() + "'失败，已存在");
        }
        int n = orderBeanMapper.updateByPrimaryKeySelective(bean);
        if(n>0){
            setCentreCache(bean);
        }
        return toAjax(n);
    }

    /**
     * 删除
     */
    @Log(title = "订购", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:register:delete")
    @GetMapping("/remove/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Integer recId)
    {

        return toAjax(orderBeanMapper.deleteByPrimaryKey(recId));
    }
}
