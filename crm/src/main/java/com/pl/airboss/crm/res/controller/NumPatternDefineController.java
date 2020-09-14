package com.pl.airboss.crm.res.controller;

import com.pl.airboss.crm.res.bean.ResPatternDefineBean;
import com.pl.airboss.crm.res.service.interfaces.IResPhoneNumSV;
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

@Controller
@RequestMapping("/crm/res/pattern")
public class NumPatternDefineController {
    private String prefix = "crm/res";

    @Autowired
    IResPhoneNumSV resPhoneNumSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("res:numPattern:view")
    @GetMapping("/numPattern")
    public String numPattern()
    {
        return prefix + "/numPattern";
    }

    @RequiresPermissions("res:numPattern:view")
    @PostMapping("/list")
    @ResponseBody
    public List<ResPatternDefineBean> list(ResPatternDefineBean bean){
        return resPhoneNumSV.queryNumPatternDefineList(bean);
    }

    /**
     * 新增
     */
    @GetMapping("/addNumPattern")
    public String add(ModelMap mmap)
    {
        return prefix + "/addNumPattern";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "号码模式", businessType = BusinessType.INSERT)
    @RequiresPermissions("res:numPattern:add")
    @PostMapping("/addNumPattern")
    @ResponseBody
    public AjaxResult addSave(@Validated ResPatternDefineBean bean)
    {

        bean.setCreateDate(new Date());
        bean.setOpId(Long.valueOf(ShiroUtils.getUserId()));
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(resPhoneNumSV.addNumPatternDefine(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/editNumPattern/{recId}")
    public String edit(@PathVariable("recId") Long recId, ModelMap mmap)
    {
        ResPatternDefineBean bean = resPhoneNumSV.queryNumPatternDefine(recId);
        mmap.put("bean", bean);
        return prefix + "/editNumPattern";
    }

    /**
     * 保存
     */
    @Log(title = "号码模式", businessType = BusinessType.UPDATE)
    @RequiresPermissions("res:numPattern:update")
    @PostMapping("/editNumPattern")
    @ResponseBody
    public AjaxResult editSave(@Validated ResPatternDefineBean bean)
    {
        return toAjax(resPhoneNumSV.updateNumPatternDefine(bean));
    }

    /**
     * 删除
     */
    @Log(title = "号码模式", businessType = BusinessType.DELETE)
    @RequiresPermissions("res:numPattern:delete")
    @GetMapping("/removeNumPattern/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Long recId)
    {

        int n = resPhoneNumSV.deleteNumPatternDefine(recId);
        if(n==0) {
            return AjaxResult.error("模式删除失败,不能被删除");
        }else{
            return success();
        }

    }
}
