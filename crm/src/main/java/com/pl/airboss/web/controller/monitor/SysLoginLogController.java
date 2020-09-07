package com.pl.airboss.web.controller.monitor;

import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.web.utils.ExcelUtil;
import com.pl.airboss.web.bean.SecLoginLogBean;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.service.ISysLoginLogService;
import com.pl.airboss.web.service.SysPasswordService;
import com.pl.airboss.web.utils.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统访问记录
 * 
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class SysLoginLogController extends BaseController
{
    private String prefix = "monitor/logininfor";

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ISysLoginLogService logininforService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("monitor:logininfor:view")
    @GetMapping()
    public String logininfor()
    {
        return prefix + "/logininfor";
    }

    @RequiresPermissions("monitor:logininfor:list")
    @PostMapping("/list")
    @ResponseBody
    public Object list(SecLoginLogBean logininfor)
    {
        startPage();
        Object r = check(logininfor);
        if(null != r)return (AjaxResult)r;

        List<SecLoginLogBean> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    Object check(SecLoginLogBean operLog){
        String d1 = (String)operLog.getParams().get("beginTime");
        String d2 = (String)operLog.getParams().get("endTime");
        if(null == d1 || "".equals(d1.trim()) || d2==null||"".equals(d2.trim())){
            return AjaxResult.error("查询条件[开始时间][结束时间]不能为空");
        }
        try {
            Date dd1 = sf.parse(d1);
            Date dd2 = sf.parse(d2);
            if (dd1.getYear() != dd2.getYear() || dd1.getMonth()!=dd2.getMonth()) {
                return AjaxResult.error("查询条件[开始时间][结束时间]要在同一个月内");
            }
            if(dd2.before(dd1)){
                return AjaxResult.error("查询条件[结束时间]要在[开始时间]之后");
            }
            if(dd2.after(new Date())){
                return AjaxResult.error("查询条件[结束时间]要在当前时间之前");
            }
            operLog.setLoginDate(sf.parse(d1));
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return null;
    }

    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:logininfor:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SecLoginLogBean logininfor)
    {
        Object r = check(logininfor);
        if(null != r)return (AjaxResult)r;
        List<SecLoginLogBean> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SecLoginLogBean> util = new ExcelUtil<SecLoginLogBean>(SecLoginLogBean.class);
        return util.exportExcel(list, "登陆日志");
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(logininforService.deleteLogininforByIds(ids));
    }
    
    @RequiresPermissions("monitor:logininfor:remove")
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        logininforService.cleanLogininfor();
        return success();
    }

    @RequiresPermissions("monitor:logininfor:unlock")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @PostMapping("/unlock")
    @ResponseBody
    public AjaxResult unlock(String loginName)
    {
        passwordService.unlock(loginName);
        return success();
    }
}
