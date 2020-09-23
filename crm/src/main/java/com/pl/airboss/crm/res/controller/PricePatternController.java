package com.pl.airboss.crm.res.controller;/**
 * Created by admin on 2020/9/9.
 */

import com.pl.airboss.crm.res.bean.ResPatternDefineBean;
import com.pl.airboss.crm.res.bean.ResSelpriceModeBean;
import com.pl.airboss.crm.res.bean.ResSelpriceModeBeanKey;
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

/**
 * @ClassName ResController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/9/9 17:27
 * @Version 1.0
 **/
@Controller
@RequestMapping("/crm/res")
public class PricePatternController {
    private String prefix = "crm/res";

    @Autowired
    IResPhoneNumSV resPhoneNumSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("res:pricePattern:view")
    @GetMapping("/pricePattern")
    public String pricePattern()
    {
        return prefix + "/pricePattern";
    }



    @RequiresPermissions("res:pricePattern:view")
    @PostMapping("/listPricePattern")
    @ResponseBody
    public List<ResSelpriceModeBean> listPricePattern(ResSelpriceModeBean bean){
        return resPhoneNumSV.queryPriceList();
    }


    @GetMapping("/checkUniquePricePattern")
    @ResponseBody
    public Boolean checkUnique(String code){
       return true;
    }

    /**
     * 新增
     */
    @GetMapping("/addPricePattern")
    public String add(ModelMap mmap)
    {
        return prefix + "/addPricePattern";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "资源-价格模式", businessType = BusinessType.INSERT)
    @RequiresPermissions("res:pricePattern:add")
    @PostMapping("/addPricePattern")
    @ResponseBody
    public AjaxResult addSave(@Validated ResSelpriceModeBean bean,long patternDefId)
    {
        //List li =resPhoneNumSV;
        /*if (null != li && li.size()>0)
        {
            return error("工单'" + bean.getTtCode() + "'失败，已存在");
        }*/
        bean.setCreateDate(new Date());
        bean.setOpId(Long.valueOf(ShiroUtils.getUserId()));
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        //更新号码模式关联的价格id
        ResPatternDefineBean b = new ResPatternDefineBean();
        b.setPatternDefId(patternDefId);
        b.setIpriceMode(bean.getResSpecId());
        resPhoneNumSV.updateNumPatternDefine(b);

        return toAjax(resPhoneNumSV.addPrice(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/editPricePattern/{recId}")
    public String edit(@PathVariable("recId") Long recId, ModelMap mmap)
    {
        ResSelpriceModeBean bean = resPhoneNumSV.queryPriceByPrimaryKey(recId);
        mmap.put("bean", bean);
        return prefix + "/editPricePattern";
    }

    /**
     * 保存
     */
    @Log(title = "资源-价格模式", businessType = BusinessType.UPDATE)
    @RequiresPermissions("res:pricePattern:update")
    @PostMapping("/editPricePattern")
    @ResponseBody
    public AjaxResult editSave(@Validated ResSelpriceModeBean bean)
    {
        return toAjax(resPhoneNumSV.updatePrice(bean));
    }

    /**
     * 删除
     */
    @Log(title = "资源-价格模式", businessType = BusinessType.DELETE)
    @RequiresPermissions("res:pricePattern:delete")
    @GetMapping("/removePricePattern/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Long recId)
    {

        int n = resPhoneNumSV.deletePrice(recId);
        if(n==0) {
            return AjaxResult.error("价格模式删除失败,不能被删除");
        }else{
            return success();
        }

    }


}
