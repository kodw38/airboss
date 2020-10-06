package com.pl.airboss.crm.res.controller;

import com.pl.airboss.crm.res.bean.ResPatternDefineBean;
import com.pl.airboss.crm.res.bean.ResPatternSegmentBean;
import com.pl.airboss.crm.res.service.interfaces.IResPhoneNumSV;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.ExcelUtil;
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

@Controller
@RequestMapping("/crm/res")
public class NumPatternDefineController extends BaseController {
    private String prefix = "crm/res";

    @Autowired
    IResPhoneNumSV resPhoneNumSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("res:numPattern:view")
    @GetMapping("/resPhonePattern")
    public String numPattern()
    {
        return prefix + "/resPhonePattern";
    }

    @RequiresPermissions("res:numPattern:view")
    @PostMapping("/listPhonePattern")
    @ResponseBody
    public TableDataInfo list(ResPatternDefineBean bean){
        startPage();
        List<ResPatternDefineBean> ls =  resPhoneNumSV.queryNumPatternDefineList(bean);
        return new TableDataInfo(ls,ls.size());
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

    /**
     * 导出
     */
    @Log(title = "导出号码模式", businessType = BusinessType.DELETE)
    @RequiresPermissions("res:numPattern:export")
    @GetMapping("/exportNumPattern")
    @ResponseBody
    public AjaxResult export(){
        ResPatternSegmentBean b = new ResPatternSegmentBean();
        b.setState("1");
        b.setResType(1L);
        List<ResPatternSegmentBean> list = resPhoneNumSV.querySegmentList(b);
        ExcelUtil eu = new ExcelUtil(ResPatternSegmentBean.class);
        return eu.exportExcel(list,"号码模式");
    }
}
