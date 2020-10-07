package com.pl.airboss.crm.product.controller;

import com.pl.airboss.crm.ac.bean.FeeInterfaceBean;
import com.pl.airboss.crm.product.bean.*;
import com.pl.airboss.crm.product.service.interfaces.IOfferSV;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.TableDataInfo;
import com.pl.airboss.web.utils.Ztree;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crm/product")
public class OfferController extends BaseController {

    private String prefix = "crm/product";

    @Autowired
    IOfferSV offerSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("offer:offer:view")
    @GetMapping("/offer")
    public String offer()
    {
        return prefix + "/offer";
    }

    /**
     * 加载资费包列表树
     */
    @GetMapping("/offerTreeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = offerSV.selectProductCategoryTree();
        return ztrees;
    }


    @RequiresPermissions("product:offer:view")
    @PostMapping("/listProduct/{categoryId}")
    @ResponseBody
    public TableDataInfo listProduct(@PathVariable("categoryId") Long categoryId){
        startPage();
        List<ProductBean> ls = offerSV.queryProductByCategory(categoryId);
        return new TableDataInfo(ls,ls.size());
    }


    @GetMapping("/checkOfferUniqueService")
    @ResponseBody
    public Boolean checkUnique(String code){
        return true;
    }

    /*
     * 新增
     */
    @GetMapping("/addOffer")
    public String add(ModelMap mmap)
    {
        return prefix + "/addOffer";
    }

    /*
     * 新增
     */
    @Log(title = "产品-新增", businessType = BusinessType.INSERT)
    @RequiresPermissions("product:offer:add")
    @PostMapping("/addOffer")
    @ResponseBody
    public AjaxResult addSave(@Validated ProductBean bean)
    {
        bean.setProductState("1");
        return toAjax(offerSV.addProduct(bean));
    }

    /*
     * 修改
     */
    @GetMapping("/editOffer/{recId}")
    public String edit(@PathVariable("recId") Long recId, ModelMap mmap)
    {
        ProductBean bean = offerSV.queryProduct(recId);
        mmap.put("bean", bean);
        return prefix + "/editOffer";
    }

    /*
     * 保存
     */
    @Log(title = "产品-修改", businessType = BusinessType.UPDATE)
    @RequiresPermissions("product:offer:update")
    @PostMapping("/editOffer")
    @ResponseBody
    public AjaxResult editSave(@Validated ProductBean bean)
    {
        return toAjax(offerSV.updateProduct(bean));
    }

    /*
     * 删除
     */
    @Log(title = "产品-删除", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:offer:delete")
    @GetMapping("/removeOffer/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Long recId)
    {

        int n = offerSV.deleteProduct(recId);
        if(n==0) {
            return AjaxResult.error("产品删除失败,不能被删除");
        }else{
            return success();
        }

    }

    @RequiresPermissions("offer:offer-fee:view")
    @GetMapping("/selOfferFee")
    public String selOfferFee()
    {
        return prefix + "/offerFee";
    }

    @RequiresPermissions("product:offer-fee:view")
    @PostMapping("/selOfferFee/{productId}")
    @ResponseBody
    public TableDataInfo selOfferFee(@PathVariable("productId") Long productId){
        startPage();
        List<FeeInterfaceBean> ls = offerSV.queryFeeInterfaceListByProductId(productId);
        return new TableDataInfo(ls,ls.size());
    }

    @Log(title = "产品资费关系-新增", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:offer-fee:add")
    @GetMapping("/addOfferFee/{prodId}/{feeId}")
    @ResponseBody
    public AjaxResult addOfferFee(@PathVariable("prodId") Integer prodId,@PathVariable("feeId") Integer feeId)
    {

        int n = offerSV.addProductFee(prodId,feeId);
        if(n==0) {
            return AjaxResult.error("产品资费关联失败");
        }else{
            return success();
        }
    }

    @Log(title = "产品资费关系-删除", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:offer-fee:delete")
    @GetMapping("/deleteOfferFee/{prodId}/{feeId}")
    @ResponseBody
    public AjaxResult deleteOfferFee(@PathVariable("prodId") Integer prodId,@PathVariable("feeId") Integer feeId)
    {

        FeePolicyBundleBean n = offerSV.getProductFeeBundle(prodId,feeId);
        if(n==null) {
            return AjaxResult.error("没有产品ID["+prodId+"]和资费ID["+feeId+"]关联");
        }else{
            int r = offerSV.deleteFeePolicyBundle(n.getFeepolicyBundId());
            if(r==0)
                return AjaxResult.error("删除失败");
                else
            return success();
        }
    }


    @RequiresPermissions("offer:offer-service:view")
    @GetMapping("/selOfferServices")
    public String selOfferServices()
    {
        return prefix + "/selOfferServices";
    }

    @RequiresPermissions("product:offer-service:view")
    @PostMapping("/selOfferServices/{productId}")
    @ResponseBody
    public TableDataInfo selOfferServices(@PathVariable("productId") Integer productId){
        startPage();
        List<ServiceBean> ls = offerSV.queryProductService(productId);
        return new TableDataInfo(ls,ls.size());
    }

    @Log(title = "产品服务关系-新增", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:offer-fee:add")
    @GetMapping("/addOfferFee/{prodId}/{serviceId}")
    @ResponseBody
    public AjaxResult addOfferService(@PathVariable("prodId") Integer prodId,@PathVariable("serviceId") Integer serviceId)
    {

        try {
            int n = offerSV.addProductService(prodId, serviceId);
            if (n == 0) {
                return AjaxResult.error("产品资费关联失败");
            } else {
                return success();
            }
        }catch (Exception e){
            return error(e.getMessage());
        }
    }

    @Log(title = "产品资费关系-删除", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:offer-fee:delete")
    @GetMapping("/deleteOfferFee/{prodId}/{serviceId}")
    @ResponseBody
    public AjaxResult deleteOfferService(@PathVariable("prodId") Integer prodId,@PathVariable("serviceId") Integer serviceId)
    {

        try {
            int n = offerSV.deleteProductService(prodId, serviceId);
            if (n == 0) {
                return AjaxResult.error("没有产品ID[" + prodId + "]和服务ID[" + serviceId + "]关联");
            } else {
                return success();
            }
        }catch (Exception e){
            return error(e.getMessage());
        }
    }
}

