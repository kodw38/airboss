package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/13.
 */

import com.pl.airboss.app.bean.CfgSensitiveBean;
import com.pl.airboss.app.dao.CfgSensitiveBeanMapper;
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
 * @ClassName SensitiveController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/13 18:23
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/sensitive")
public class SensitiveController {
    private String prefix = "app/sensitive";



    @Autowired
    CfgSensitiveBeanMapper sensitiveBeanMapper;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    @RequiresPermissions("app:sensitive:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/sensitive";
    }

    @RequiresPermissions("app:sensitive:search")
    @PostMapping("/list")
    @ResponseBody
    public List<CfgSensitiveBean> list(CfgSensitiveBean bean){
        return sensitiveBeanMapper.selectList(bean);
    }

    @GetMapping("/checkUnique")
    @ResponseBody
    public Boolean checkUnique(String content){
        CfgSensitiveBean b = new CfgSensitiveBean();
        b.setContent(content);
        List<CfgSensitiveBean> r = sensitiveBeanMapper.selectList(b);
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

    /**
     * 新增保存部门
     */
    @Log(title = "敏感词", businessType = BusinessType.INSERT)
    @RequiresPermissions("app:sensitive:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated CfgSensitiveBean bean)
    {
        List li =sensitiveBeanMapper.selectList(bean);
        if (null != li && li.size()>0)
        {
            return error("敏感词'" + bean.getContent() + "'失败，已存在");
        }
        bean.setCreateDate(new Date());
        bean.setOpId(ShiroUtils.getUserId());
        bean.setState(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(sensitiveBeanMapper.insert(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{recId}")
    public String edit(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        CfgSensitiveBean bean = sensitiveBeanMapper.selectByPrimaryKey(recId);
        mmap.put("bean", bean);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "敏感词", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:sensitive:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated CfgSensitiveBean bean)
    {
        return toAjax(sensitiveBeanMapper.updateByPrimaryKeySelective(bean));
    }

    /**
     * 删除
     */
    @Log(title = "敏感词", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:sensitive:delete")
    @GetMapping("/remove/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Integer recId)
    {

        return toAjax(sensitiveBeanMapper.deleteByPrimaryKey(recId));
    }

}
