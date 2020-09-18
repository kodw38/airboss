package com.pl.airboss.crm.res.controller;

import com.pl.airboss.crm.res.bean.ResPhoneNumOriginBean;
import com.pl.airboss.crm.res.service.interfaces.IResPhoneNumSV;
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

import java.util.Date;
import java.util.List;

import static com.pl.airboss.web.utils.AjaxResult.error;
import static com.pl.airboss.web.utils.AjaxResult.success;

@RequestMapping("/crm/res/numphone")
@Controller
public class NumPhoneController extends BaseController {

    private String prefix = "crm/res";

    @Autowired
    IResPhoneNumSV resPhoneNumSV;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }


    @RequiresPermissions("res:numPhone:view")
    @GetMapping("/numPhone")
    public String numPattern()
    {
        return prefix + "/numPhone";
    }

    @RequiresPermissions("res:numPhone:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ResPhoneNumOriginBean bean){
        startPage();
        List<ResPhoneNumOriginBean>ls =  resPhoneNumSV.queryNum(bean,0,0);
        return getDataTable(ls);
    }

    /**
     * 修改
     */
    @GetMapping("/editNumPhone/{recId}")
    public String edit(@PathVariable("recId") String recId, ModelMap mmap)
    {
        ResPhoneNumOriginBean bean = resPhoneNumSV.queryNumById(recId);
        mmap.put("bean", bean);
        return prefix + "/editNumPhone";
    }

    /**
     * 保存
     */
    @Log(title = "号码", businessType = BusinessType.UPDATE)
    @RequiresPermissions("res:numPhone:update")
    @PostMapping("/editNumPhone")
    @ResponseBody
    public AjaxResult editSave(@Validated ResPhoneNumOriginBean bean)
    {
        return toAjax(resPhoneNumSV.updateNum(bean));
    }


    /**
     * 号码预占
     */
    @Log(title = "号码预占", businessType = BusinessType.UPDATE)
    @RequiresPermissions("res:numPhone:occupy")
    @PostMapping("/occupyNumPhone")
    @ResponseBody
    public AjaxResult numPhoneOccupy(String phoneNum)
    {

        try {
            return toAjax(resPhoneNumSV.occupyPhoneNum(phoneNum));
        }catch (Exception e){
            return new AjaxResult(AjaxResult.Type.ERROR,e.getMessage());
        }
    }

    /**
     * 号码预占释放
     */
    @Log(title = "号码预占释放", businessType = BusinessType.UPDATE)
    @RequiresPermissions("res:numPhone:release")
    @PostMapping("/releaseNumPhone")
    @ResponseBody
    public AjaxResult numPhoneRelease(String phoneNum)
    {
        try {
            return toAjax(resPhoneNumSV.releasePhoneNum(phoneNum));
        }catch (Exception e){
            return new AjaxResult(AjaxResult.Type.ERROR,e.getMessage());
        }
    }

}
