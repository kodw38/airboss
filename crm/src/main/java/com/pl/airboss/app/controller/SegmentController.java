package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/14.
 */

import com.pl.airboss.app.bean.ResPhoneNumSegmentBean;
import com.pl.airboss.app.dao.ResPhoneNumSegmentBeanMapper;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.web.utils.AjaxResult;
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
 * @ClassName SegmentController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/14 11:33
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/segment")
public class SegmentController {
    private String prefix = "app/segment";



    @Autowired
    ResPhoneNumSegmentBeanMapper segmentBeanMapper;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @RequiresPermissions("app:segment:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/segment";
    }

    @RequiresPermissions("app:segment:search")
    @PostMapping("/list")
    @ResponseBody
    public List<ResPhoneNumSegmentBean> list(ResPhoneNumSegmentBean bean){
        return segmentBeanMapper.selectList(bean);
    }

    @GetMapping("/checkUnique")
    @ResponseBody
    public Boolean checkUnique(@RequestParam(name="begNum") String begNum,@RequestParam(name="segType") Integer segType){
        ResPhoneNumSegmentBean b = new ResPhoneNumSegmentBean();
        b.setSegType(segType);
        b.setBegNum(begNum);
        List<ResPhoneNumSegmentBean> r = segmentBeanMapper.selectList(b);
        if(null != r && r.size()>0){
            return false;
        }else{
            return true;
        }
    }


    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }



    @Log(title = "号段", businessType = BusinessType.INSERT)
    @RequiresPermissions("app:segment:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ResPhoneNumSegmentBean bean)
    {
        List li =segmentBeanMapper.selectList(bean);
        if (null != li && li.size()>0)
        {
            return AjaxResult.error("号段'" +"" + "'失败，已存在");
        }
        bean.setDoneDate(new Date());
        bean.setState(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(segmentBeanMapper.insert(bean));
    }

    @GetMapping("/edit/{recId}")
    public String edit(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        ResPhoneNumSegmentBean bean = segmentBeanMapper.selectByPrimaryKey(recId);
        mmap.put("bean", bean);
        return prefix + "/edit";
    }



    @Log(title = "号段", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:segment:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated ResPhoneNumSegmentBean bean)
    {
        return toAjax(segmentBeanMapper.updateByPrimaryKeySelective(bean));
    }


    @Log(title = "号段", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:segment:delete")
    @GetMapping("/remove/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Integer recId)
    {

        return toAjax(segmentBeanMapper.deleteByPrimaryKey(recId));
    }
}
