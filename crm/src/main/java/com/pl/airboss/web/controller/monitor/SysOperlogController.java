package com.pl.airboss.web.controller.monitor;

import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.web.bean.SecOpLogBean;
import com.pl.airboss.web.service.ISysOperLogService;
import com.pl.airboss.web.utils.ExcelUtil;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 操作日志记录
 * 
 */
@Controller
@RequestMapping("/monitor/operlog")
public class SysOperlogController extends BaseController
{
    private String prefix = "monitor/operlog";

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ISysOperLogService operLogService;

    @RequiresPermissions("monitor:operlog:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/operlog";
    }

    @RequiresPermissions("monitor:operlog:list")
    @PostMapping("/list")
    @ResponseBody
    public Object list(SecOpLogBean operLog)
    {
        startPage();
        Object r = check(operLog);
        if(null != r)return r;
        List<SecOpLogBean> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }
    Object check(SecOpLogBean operLog){
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
            operLog.setDoneDate(sf.parse(d1));
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return null;
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:operlog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SecOpLogBean operLog)
    {
        Object r = check(operLog);
        if(null != r)return (AjaxResult)r;
        List<SecOpLogBean> list = operLogService.selectOperLogList(operLog);
        ExcelUtil<SecOpLogBean> util = new ExcelUtil<SecOpLogBean>(SecOpLogBean.class);
        return util.exportExcel(list, "操作日志");
    }

    @RequiresPermissions("monitor:operlog:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids,Date downDate)
    {
        return toAjax(operLogService.deleteOperLogByIds(ids,downDate));
    }

    @RequiresPermissions("monitor:operlog:detail")
    @GetMapping("/detail/{operId}")
    public String detail(@PathVariable("operId") String operId ,ModelMap mmap)
    {
        String[] ts = operId.split("\\|");
        int id = Integer.parseInt(ts[0]);
        try {
            Date doneDate = sf.parse(ts[1]);
            mmap.put("operLog", operLogService.selectOperLogById(id, doneDate));
        }catch (Exception e){

        }
        return prefix + "/detail";

    }
    
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:operlog:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        operLogService.cleanOperLog();
        return success();
    }
}
