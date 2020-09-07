package com.pl.airboss.app.controller;/**
 * Created by admin on 2020/5/17.
 */

import com.pl.airboss.app.bean.MSTTBean;
import com.pl.airboss.app.dao.MSTTBeanMapper;
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
 * @ClassName MsttController
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/17 8:40
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app/tt")
public class MsttController {
    private String prefix = "app/tt";


    @Autowired
    MSTTBeanMapper msttBeanMapper;

    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    @RequiresPermissions("app:tt:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/tt";
    }

    @RequiresPermissions("app:ttreply:view")
    @GetMapping("/reply")
    public String reply()
    {
        return prefix + "/reply";
    }

    @RequiresPermissions("app:ttreply:update")
    @GetMapping("/replyedit/{recId}")
    public String replyedit(@PathVariable("recId") Integer recId,ModelMap mmap){
        MSTTBean bean = msttBeanMapper.selectByPrimaryKey(recId);
        mmap.put("bean", bean);
        return prefix+"/replyedit";
    }

    @RequiresPermissions("app:cfgtt:view")
    @GetMapping("/selectCfgMSTT")
    public String selectCfgMSTT(){
        return prefix+"/selectCfgMSTT";
    }

    @RequiresPermissions("app:tt:view")
    @PostMapping("/list")
    @ResponseBody
    public List<MSTTBean> list(MSTTBean bean){
        return msttBeanMapper.selectList(bean);
    }

    @RequiresPermissions("app:ttreply:view")
    @PostMapping("/listself")
    @ResponseBody
    public List<MSTTBean> listself(MSTTBean bean){
        bean.setOperatorCode(ShiroUtils.getLoginCode());
        return msttBeanMapper.selectList(bean);
    }
    @GetMapping("/checkUnique")
    @ResponseBody
    public Boolean checkUnique(String ttCode){
        MSTTBean b = new MSTTBean();
        b.setTtCode(ttCode);
        List<MSTTBean> r = msttBeanMapper.selectList(b);
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

    @GetMapping("/selectUser")
    public String selectUser(ModelMap mmap){
        return prefix+"/selectUser";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "工单", businessType = BusinessType.INSERT)
    @RequiresPermissions("app:tt:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated MSTTBean bean)
    {
        List li =msttBeanMapper.selectList(bean);
        if (null != li && li.size()>0)
        {
            return error("工单'" + bean.getTtCode() + "'失败，已存在");
        }
        bean.setCreateDate(new Date());
        bean.setOpId(ShiroUtils.getUserId());
        bean.setState(1);
        //dept.setCreateBy(ShiroUtils.getLoginCode());
        return toAjax(msttBeanMapper.insert(bean));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{recId}")
    public String edit(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        MSTTBean bean = msttBeanMapper.selectByPrimaryKey(recId);
        mmap.put("bean", bean);
        return prefix + "/edit";
    }

    /**
     * 修改
     */
    @GetMapping("/view/{recId}")
    public String view(@PathVariable("recId") Integer recId, ModelMap mmap)
    {
        MSTTBean bean = msttBeanMapper.selectByPrimaryKey(recId);
        mmap.put("bean", bean);
        return prefix + "/view";
    }

    /**
     * 保存
     */
    @Log(title = "工单", businessType = BusinessType.UPDATE)
    @RequiresPermissions("app:tt:update")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated MSTTBean bean)
    {
        return toAjax(msttBeanMapper.updateByPrimaryKeySelective(bean));
    }

    /**
     * 删除
     */
    @Log(title = "工单", businessType = BusinessType.DELETE)
    @RequiresPermissions("app:tt:delete")
    @GetMapping("/remove/{recId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("recId") Integer recId)
    {

        int n = msttBeanMapper.deleteByPrimaryKey(recId);
        if(n==0) {
            return AjaxResult.error("该工单正在处理中,不能被删除");
        }else{
            return success();
        }

    }


    /**
     *认领
     *@Description
     *@auther Kod Wong
     *@Date 2020/5/19 16:32
     *@Param
     *@return
     *@Version 1.0
     */
    @Log(title = "工单", businessType = BusinessType.IMPORT)
    @RequiresPermissions("app:ttreply:delete")
    @GetMapping("/take/{recId}")
    @ResponseBody
    public AjaxResult take(@PathVariable("recId") Integer recId){
        return toAjax(msttBeanMapper.takeByPrimaryKey(recId));
    }

}
