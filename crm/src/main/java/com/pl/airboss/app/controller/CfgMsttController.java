package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/15.
 */

import com.pl.airboss.app.bean.CfgMSTTBean;
import com.pl.airboss.app.dao.CfgMSTTBeanMapper;
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
 * @ClassName CfgMsttController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/15 11:26
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/cfgtt")
public class CfgMsttController {
    private String prefix = "app/cfgtt";


    @Autowired
    CfgMSTTBeanMapper cfgMSTTBeanMapper;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @RequiresPermissions("app:cfgtt:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/cfgtt";
    }

    @RequiresPermissions("app:cfgtt:view")
    @PostMapping("/list")
    @ResponseBody
    public List<CfgMSTTBean> list(CfgMSTTBean bean){
        return cfgMSTTBeanMapper.selectList(bean);
    }

    @GetMapping("/checkUnique")
    @ResponseBody
    public Boolean checkUnique(String ttCode){
        CfgMSTTBean b = new CfgMSTTBean();
        b.setTtCode(ttCode);
        List<CfgMSTTBean> r = cfgMSTTBeanMapper.selectList(b);
        if(null != r && r.size()>0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 新增
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    @RequiresPermissions("app:cfgtt:view")
    @GetMapping("/selectUser")
    public String selectUser(ModelMap mmap){
        return prefix+"/selectUser";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "工单配置", businessType = BusinessType.INSERT)
    @RequiresPermissions("app:cfgtt:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated CfgMSTTBean bean)
    {
        List li =cfgMSTTBeanMapper.selectList(bean);
        if (null != li && li.size()>0)
        {
            return AjaxResult.error("工单配置'" + bean.getTtCode() + "'失败，已存在");
        }
        bean.setCreateDate(new Date());
        bean.setOpId(ShiroUtils.getUserId());
        bean.setState(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(cfgMSTTBeanMapper.insert(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{recId}")
    public String edit(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        CfgMSTTBean bean = cfgMSTTBeanMapper.selectByPrimaryKey(recId);
        mmap.put("bean", bean);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "工单配置", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:cfgtt:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated CfgMSTTBean bean)
    {
        return toAjax(cfgMSTTBeanMapper.updateByPrimaryKeySelective(bean));
    }

    /**
     * 删除
     */
    @Log(title = "工单配置", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:cfgtt:delete")
    @GetMapping("/remove/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Integer recId)
    {

        return toAjax(cfgMSTTBeanMapper.deleteByPrimaryKey(recId));
    }
}
