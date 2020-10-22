package com.pl.airboss.crm.product.controller;

import com.pl.airboss.crm.product.bean.ServiceBean;
import com.pl.airboss.crm.product.bean.ServiceParamBean;
import com.pl.airboss.crm.product.service.interfaces.IOfferSV;
import com.pl.airboss.crm.res.bean.ResPatternDefineBean;
import com.pl.airboss.crm.res.bean.ResPatternSegmentBean;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
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
public class ServicePropertyController extends BaseController {
    private String prefix = "crm/product";

    @Autowired
    IOfferSV offerSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    @RequiresPermissions("product:service:view")
    @GetMapping("/serviceProperty/{serviceId}")
    public String serviceProperty(@PathVariable("serviceId") Integer serviceId, ModelMap mmap) {
        ServiceBean serviceBean = offerSV.queryServiceById(serviceId);
        mmap.put("bean", serviceBean);
        return prefix + "/serviceProperty";
    }

    @RequiresPermissions("product:serviceProperty:view")
   // @PostMapping("/listServiceProperties/{serviceId}")
    @PostMapping("/listServiceProperties")
    @ResponseBody
   // public TableDataInfo list(@PathVariable("serviceId") int serviceId){
    public TableDataInfo list(@RequestParam(value = "serviceId") int serviceId){
   // public TableDataInfo list(ServiceParamBean bean){
        startPage();
        List<ServiceParamBean> ls = offerSV.queryServiceParams(serviceId);
        return new TableDataInfo(ls,ls.size());
    }


    @PostMapping("/checkPropertyUnique")
    @ResponseBody
    public Boolean checkPropertyUnique(ServiceParamBean bean){
        return !offerSV.checkPropertyUnique(bean.getParamId(),bean.getParamName(),bean.getServiceId());
    }

    /**
     * 新增
     */
    @GetMapping("/addServiceProperties/{serviceId}")
    public String add(@PathVariable("serviceId") Integer serviceId, ModelMap mmap)
    {
        mmap.put("serviceId", serviceId);
        return prefix + "/addServiceProperties";
    }

    /**
     * 新增
     */
    @Log(title = "产品-服务-属性-新增", businessType = BusinessType.INSERT)
    @RequiresPermissions("product:serviceProperty:add")
    @PostMapping("/addServiceProperty")
    @ResponseBody
    public AjaxResult addSave(@Validated ServiceParamBean bean)
    {
        return toAjax(offerSV.addServiceParam(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/editServiceProperty/{propertyId}")
    public String edit(@PathVariable("propertyId") Long propertyId, ModelMap mmap)
    {
        ServiceParamBean bean = offerSV.queryServiceParam(propertyId);
        mmap.put("bean", bean);
        return prefix + "/editServiceProperty";
    }

    /**
     * 保存
     */
    @Log(title = "产品-服务-服务-修改", businessType = BusinessType.UPDATE)
    @RequiresPermissions("product:serviceProperty:eidt")
    @PostMapping("/editServiceProperty")
    @ResponseBody
    public AjaxResult editSave(@Validated ServiceParamBean bean)
    {
        return toAjax(offerSV.updateServiceParam(bean));
    }

    /**
     * 删除
     */
    @Log(title = "产品-服务-服务-删除", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:serviceProperty:remove")
    @PostMapping("/removeServiceProperty")
    @ResponseBody
    public AjaxResult remove(@RequestParam(value = "ids")  Long propertyId)
    {

        int n = offerSV.deleteServiceParam(propertyId);
        if(n==0) {
            return AjaxResult.error("服务属性删除失败,不能被删除");
        }else{
            return success();
        }

    }

}
