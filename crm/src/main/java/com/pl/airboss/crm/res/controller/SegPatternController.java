package com.pl.airboss.crm.res.controller;

import com.pl.airboss.crm.res.bean.ResPatternSegmentBean;
import com.pl.airboss.crm.res.service.interfaces.IResPhoneNumSV;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.utils.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.pl.airboss.web.utils.AjaxResult.error;
import static com.pl.airboss.web.utils.AjaxResult.success;

@Controller
@RequestMapping("/crm/res")
public class SegPatternController extends BaseController {
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
    public TableDataInfo list(ResPatternSegmentBean bean){
        startPage();
        List<ResPatternSegmentBean> ls = resPhoneNumSV.querySegmentList(bean);
        return new TableDataInfo(ls,ls.size());
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

    @RequiresPermissions("res:segment:view")
    @GetMapping("/getPhoneSegmentTemplate")
    public String linkSegmentTemplate(){
        return prefix+"NumSegment.xlsx";
    }

    static SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
    public AjaxResult importData(@RequestParam("segmentFile") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            List<Map> numSegment = ExcelUtil.readFromSecondRow("NumSegment", inputStream);
            if(null != numSegment) {
                List<ResPatternSegmentBean> list =new ArrayList<>();
                for(Map m:numSegment) {
                    ResPatternSegmentBean  b = new ResPatternSegmentBean();
                    if(StringUtils.isNotEmpty((String)m.get("归属网元")))
                    b.setNetId((String)m.get("归属网元"));
                    b.setState("1");
                    if(StringUtils.isNotEmpty((String)m.get("号段名称")))
                    b.setPatternSegName((String)m.get("号段名称"));
                    if(StringUtils.isNotEmpty((String)m.get("号段表达式")))
                    b.setSegExp((String)m.get("号段表达式"));
                    b.setCreateDate(new Date());
                    if(StringUtils.isNotEmpty((String)m.get("生效时间")))
                    b.setEffectiveDate(sf.parse((String)m.get("生效时间")));
                    if(StringUtils.isNotEmpty((String)m.get("失效时间")))
                    b.setExpireDate(sf.parse((String)m.get("失效时间")));
                    b.setResType(1L);
                    list.add(b);
                }
                resPhoneNumSV.importSegmentList(list);
                return success();
            }
            return error();
        }catch (Exception e){
            return error("导入号段失败");
        }
    }

    @Log(title = "生成号码", businessType = BusinessType.GRANT)
    @RequiresPermissions("res:segPattern:num_generate")
    @GetMapping("/generateNumBySegPattern/{recId}")
    @ResponseBody
    public AjaxResult generate(@PathVariable("recId") Long recId)
    {
        try{
            boolean b = resPhoneNumSV.generatorNumBySegment(recId);
            if(b)
            return success();
            else
                return error();
        }catch (Exception e){
            return error(e.getMessage());
        }

    }


    /**
     * 号段模式状态修改
     */
    @Log(title = "号段模式状态修改", businessType = BusinessType.UPDATE)
    @RequiresPermissions("crm.res:resPhoneSegment:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(ResPatternSegmentBean bean) {
        return toAjax(resPhoneNumSV.changeStatus(bean));
    }
}
