package com.pl.airboss.crm.product.controller;

//import com.pl.airboss.crm.ac.bean.FeeInterfaceBean;
import com.pl.airboss.crm.ac.bean.FeeCycleBean;
import com.pl.airboss.crm.ac.bean.FeeInterfaceBean;
import com.pl.airboss.crm.product.bean.FeePolicyBean;
import com.pl.airboss.crm.product.bean.FeePolicyBundleBean;
import com.pl.airboss.crm.product.service.interfaces.IOfferSV;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.TableDataInfo;
import com.pl.airboss.web.utils.Ztree;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/crm/product")
@Controller
public class FeeController extends BaseController {
    private String prefix = "crm/product";

    @Autowired
    IOfferSV offerSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("product:fee:view")
    @GetMapping("/feeCfg")
    public String pricePattern()
    {
        return prefix + "/feeCfg";
    }


    /**
     * 加载资费包列表树
     */
    @GetMapping("/feeTreeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = offerSV.selectFeePolicyBundleTree(new FeePolicyBundleBean());

        return ztrees;
    }


    @RequiresPermissions("product:fee:view")
    @PostMapping("/listFee")
    @ResponseBody
    public TableDataInfo list(FeeInterfaceBean bean){
        startPage();
        List<FeeInterfaceBean> ls = offerSV.queryFeeInterface(bean);
        return new TableDataInfo(ls,ls.size());
    }

    @RequiresPermissions("product:fee:view")
    @PostMapping("/listFeeByBundleId")
    @ResponseBody
    public TableDataInfo list(Long bundleId){
        startPage();
        List<FeeInterfaceBean> ls = offerSV.queryFeeInterfaceByBundleId(bundleId);
        return new TableDataInfo(ls,ls.size());
    }

    @RequiresPermissions("product:fee:view")
    @PostMapping("/listCycle")
    @ResponseBody
    public List<FeeCycleBean> listCycle(){
        return offerSV.listCycle();
    }

    @GetMapping("/checkFeeUniqueService")
    @ResponseBody
    public Boolean checkUnique(String code){
        return true;
    }

    /*
     * 新增
    */
    @GetMapping("/addFee")
    public String add(ModelMap mmap)
    {
        return prefix + "/addFee";
    }

    /*
     * 新增
    */
    @Log(title = "产品-资费-新增", businessType = BusinessType.INSERT)
    @RequiresPermissions("product:fee:add")
    @PostMapping("/addFee")
    @ResponseBody
    public AjaxResult addSave(@Validated FeeInterfaceBean bean)
    {
        bean.setForBossTag("1");
        return toAjax(offerSV.addFeeInterface(bean));
    }

    /*
     * 修改
    */
    @GetMapping("/editFee/{recId}")
    public String edit(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        FeeInterfaceBean bean = offerSV.getFeeInterface(recId);
        mmap.put("bean", bean);
        return prefix + "/editFee";
    }

    /*
     * 保存
    */
    @Log(title = "产品-资费-修改", businessType = BusinessType.UPDATE)
    @RequiresPermissions("product:fee:update")
    @PostMapping("/editFee")
    @ResponseBody
    public AjaxResult editSave(@Validated FeeInterfaceBean bean)
    {
        return toAjax(offerSV.updateFeeInterface(bean));
    }

    /*
     * 删除
*/
    @Log(title = "产品-资费-删除", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:fee:delete")
    @GetMapping("/removeFee/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Integer recId)
    {

        int n = offerSV.deleteFeeInterface(recId);
        if(n==0) {
            return AjaxResult.error("资费删除失败,不能被删除");
        }else{
            return success();
        }

    }
}
