package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/13.
 */

import com.pl.airboss.app.bean.CfgBlackWhiteBean;
import com.pl.airboss.app.dao.CfgBlackWhiteBeanMapper;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.ShiroUtils;
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
 * @ClassName BlackWhiteController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/13 12:14
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/blacklist")
public class BlackWhiteController {
    private String prefix = "app/blacklist";



    @Autowired
    CfgBlackWhiteBeanMapper blackWhiteBeanMapper;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @RequiresPermissions("app:blacklist:view")
    @GetMapping()
    public String blackList()
    {
        return prefix + "/blacklist";
    }

    @RequiresPermissions("app:blacklist:view")
    @PostMapping("/list")
    @ResponseBody
    public List<CfgBlackWhiteBean> list(CfgBlackWhiteBean bean){
        return blackWhiteBeanMapper.selectList(bean);
    }

    /**
     * 新增部门
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "黑白名单", businessType = BusinessType.INSERT)
    @RequiresPermissions("app:blacklist:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated CfgBlackWhiteBean bean)
    {
        CfgBlackWhiteBean q = new CfgBlackWhiteBean();
        q.setBillId(bean.getBillId());
        q.setState(1);
        List li =blackWhiteBeanMapper.selectList(q);
        if (null != li && li.size()>0)
        {
            return AjaxResult.error("黑白名单'" + bean.getBillId() + "'失败，已存在");
        }
        bean.setCreateDate(new Date());
        bean.setOpId(ShiroUtils.getUserId());
        bean.setState(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(blackWhiteBeanMapper.insert(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{billId}")
    public String edit(@PathVariable("billId") String billId, ModelMap mmap)
    {
        CfgBlackWhiteBean bean = blackWhiteBeanMapper.selectByPrimaryKey(billId);
        mmap.put("bean", bean);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "黑白名单", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:blacklist:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated CfgBlackWhiteBean bean)
    {
        try {
            return toAjax(blackWhiteBeanMapper.updateByPrimaryKeySelective(bean));
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @Log(title = "黑白名单", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:blacklist:delete")
    @GetMapping("/remove/{billId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("billId") String billId)
    {

        return toAjax(blackWhiteBeanMapper.deleteByPrimaryKey(billId));
    }

    @PostMapping("/checkBlackUnique")
    @ResponseBody
    public boolean check(CfgBlackWhiteBean bean){

        CfgBlackWhiteBean q = new CfgBlackWhiteBean();
        q.setBillId(bean.getBillId());
        q.setState(1);
        List li =blackWhiteBeanMapper.selectList(q);
        if (null != li && li.size()>0)
        {
            return false;
        }else{
            return true;
        }
    }

}
