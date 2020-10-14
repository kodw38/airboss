package com.pl.airboss.crm.product.controller;

import com.pl.airboss.crm.product.bean.ServiceBean;
import com.pl.airboss.crm.product.service.interfaces.IOfferSV;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
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

import java.util.List;
@RequestMapping("/crm/product")
@Controller
public class ServiceController extends BaseController {
    private String prefix = "crm/product";

    @Autowired
    IOfferSV offerSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("product:service:view")
    @GetMapping("/servicecfg")
    public String pricePattern()
    {
        return prefix + "/servicecfg";
    }



    @RequiresPermissions("product:service:view")
    @PostMapping("/listServices")
    @ResponseBody
    public TableDataInfo list(ServiceBean bean){
        startPage();
        List<ServiceBean> ls = offerSV.queryServices(bean);
        return new TableDataInfo(ls,ls.size());
    }


    @GetMapping("/checkServiceUniqueService")
    @ResponseBody
    public Boolean checkUnique(String code){
        return true;
    }

    /**
     * 新增
     */
    @GetMapping("/addService")
    public String add(ModelMap mmap)
    {
        return prefix + "/addService";
    }

    /**
     * 新增
     */
    @Log(title = "产品-服务-新增", businessType = BusinessType.INSERT)
    @RequiresPermissions("product:service:add")
    @PostMapping("/addService")
    @ResponseBody
    public AjaxResult addSave(@Validated ServiceBean bean)
    {
        bean.setServiceState("1");
        return toAjax(offerSV.addService(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/editService/{recId}")
    public String edit(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        ServiceBean bean = offerSV.queryServiceById(recId);
        mmap.put("bean", bean);
        return prefix + "/editService";
    }

    /**
     * 保存
     */
    @Log(title = "产品-服务-修改", businessType = BusinessType.UPDATE)
    @RequiresPermissions("product:service:update")
    @PostMapping("/editService")
    @ResponseBody
    public AjaxResult editSave(@Validated ServiceBean bean)
    {
        return toAjax(offerSV.updateService(bean));
    }

    /**
     * 删除
     */
    @Log(title = "产品-服务-删除", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:service:delete")
    @GetMapping("/removeService/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Integer recId)
    {

        int n = offerSV.deleteService(recId);
        if(n==0) {
            return AjaxResult.error("服务删除失败,不能被删除");
        }else{
            return success();
        }

    }

}