package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/14.
 */

import com.pl.airboss.app.bean.CfgResPriceBean;
import com.pl.airboss.app.dao.CfgResPriceBeanMapper;
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
import java.util.LinkedList;
import java.util.List;

import static com.pl.airboss.web.utils.AjaxResult.error;
import static com.pl.airboss.web.utils.AjaxResult.success;

/**
 * @ClassName ResPriceController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/14 13:26
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/price")
public class ResPriceController {
    private String prefix = "app/price";



    @Autowired
    CfgResPriceBeanMapper priceBeanMapper;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    @RequiresPermissions("app:price:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/price";
    }

    @RequiresPermissions("app:price:search")
    @PostMapping("/list")
    @ResponseBody
    public List<CfgResPriceBean> list(CfgResPriceBean bean){
        return priceBeanMapper.selectList(bean);
    }

    @GetMapping("/checkUnique")
    @ResponseBody
    public Boolean checkUnique(CfgResPriceBean bean){
        /*List<CfgResPriceBean> r = priceBeanMapper.selectList(bean);
        if(null != r && r.size()>0){
            return false;
        }else{
            return true;
        }*/
        return true;
    }


    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("RateList",getRateList());
        return prefix + "/add";
    }



    @Log(title = "价格设置", businessType = BusinessType.INSERT)
    @RequiresPermissions("app:price:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated CfgResPriceBean bean)
    {
        List li =priceBeanMapper.selectList(bean);
        if (null != li && li.size()>0)
        {
            return error("价格设置'" +"" + "'失败，已存在");
        }
        bean.setDoneDate(new Date());
        bean.setState(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(priceBeanMapper.insert(bean));
    }

    @GetMapping("/edit/{recId}")
    public String edit(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        CfgResPriceBean bean = priceBeanMapper.selectByPrimaryKey(recId);
        mmap.put("bean", bean);
        mmap.put("RateList",getRateList());
        return prefix + "/edit";
    }

    List<Integer> getRateList(){
        List list = new LinkedList();
        for(int i=0;i<=100;i++){
            list.add(i);
        }
        return list;
    }


    @Log(title = "价格设置", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:price:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated CfgResPriceBean bean)
    {
        return toAjax(priceBeanMapper.updateByPrimaryKeySelective(bean));
    }


    @Log(title = "价格设置", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:price:delete")
    @GetMapping("/remove/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Integer recId)
    {

        return toAjax(priceBeanMapper.deleteByPrimaryKey(recId));
    }
}
