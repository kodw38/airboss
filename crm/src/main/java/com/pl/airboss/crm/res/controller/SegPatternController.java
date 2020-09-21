package com.pl.airboss.crm.res.controller;

import com.pl.airboss.crm.res.bean.ResPatternSegmentBean;
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
@RequestMapping("/crm/res")
public class SegPatternController {
    private String prefix = "crm/res";

    @Autowired
    IResPhoneNumSV resPhoneNumSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("res:segPattern:view")
    @GetMapping("/resPhoneSegment")
    public String segPattern()
    {
        return prefix + "/resPhoneSegment";
    }

    @RequiresPermissions("res:segPattern:view")
    @PostMapping("/listPhoneSegment")
    @ResponseBody
    public List<ResPatternSegmentBean> list(ResPatternSegmentBean bean){
        return resPhoneNumSV.querySegmentList(bean);
    }

    /**
     * 新增
     */
    @GetMapping("/addSegPattern")
    public String add(ModelMap mmap)
    {
        return prefix + "/addSegPattern";
    }

    /**
     * 保存
     */
    @Log(title = "号段模式", businessType = BusinessType.INSERT)
    @RequiresPermissions("res:segPattern:add")
    @PostMapping("/addSegPattern")
    @ResponseBody
    public AjaxResult addSave(@Validated ResPatternSegmentBean bean)
    {

        bean.setCreateDate(new Date());
        bean.setOpId(Long.valueOf(ShiroUtils.getUserId()));
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(resPhoneNumSV.addSegmentPattern(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/editSegPattern/{recId}")
    public String edit(@PathVariable("recId") Long recId, ModelMap mmap)
    {
        ResPatternSegmentBean bean = resPhoneNumSV.querySegmentPattern(recId);
        mmap.put("bean", bean);
        return prefix + "/editSegPattern";
    }

    /**
     * 保存
     */
    @Log(title = "号段模式", businessType = BusinessType.UPDATE)
    @RequiresPermissions("res:segPattern:update")
    @PostMapping("/editSegPattern")
    @ResponseBody
    public AjaxResult editSave(@Validated ResPatternSegmentBean bean)
    {
        return toAjax(resPhoneNumSV.updateSegment(bean));
    }

    /**
     * 删除
     */
    @Log(title = "号段模式", businessType = BusinessType.DELETE)
    @RequiresPermissions("res:segPattern:delete")
    @GetMapping("/removeSegPattern/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Long recId)
    {

        int n = resPhoneNumSV.deleteSegment(recId);
        if(n==0) {
            return error("模式删除失败,不能被删除");
        }else{
            return success();
        }

    }
}
