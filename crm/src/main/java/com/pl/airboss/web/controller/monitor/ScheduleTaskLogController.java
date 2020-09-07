package com.pl.airboss.web.controller.monitor;

import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.framework.bean.CfgScheduleTaskBean;
import com.pl.airboss.framework.bean.ScheduleTaskLog;
import com.pl.airboss.framework.dao.CfgScheduleTaskBeanMapper;
import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.service.IScheduleTaskLogService;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class ScheduleTaskLogController extends BaseController
{
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    private String prefix = "monitor/job";

    @Autowired
    private CfgScheduleTaskBeanMapper jobService;

    @Autowired
    private IScheduleTaskLogService jobLogService;

    @RequiresPermissions("monitor:job:view")
    @GetMapping()
    public String jobLog(@RequestParam(value = "jobId", required = false) Integer jobId,@RequestParam(value = "taskCode", required = false) String taskCode, ModelMap mmap)
    {
        if (StringUtils.isNotNull(jobId))
        {
            CfgScheduleTaskBean job = jobService.selectByPrimaryKey(jobId);
            mmap.put("job", job);
        }
        if (StringUtils.isNotNull(taskCode))
        {
            mmap.put("taskCode", taskCode);
        }
        return prefix + "/jobLog";
    }

    Object check(ScheduleTaskLog operLog){
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
            operLog.setCreateTime(sf.parse(d1));
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return null;
    }

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(value = "taskCode", required = false) String taskCode,ScheduleTaskLog jobLog)
    {
        startPage();
        Object r = check(jobLog);
        if(null != r)return r;
        if(null != taskCode&& !"".equalsIgnoreCase(taskCode))
            jobLog.setJobName(taskCode);
        List<ScheduleTaskLog> list = jobLogService.selectJobLogList(jobLog);
        return getDataTable(list);
    }

    @Log(title = "调度日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ScheduleTaskLog jobLog)
    {
        List<ScheduleTaskLog> list = jobLogService.selectJobLogList(jobLog);
        ExcelUtil<ScheduleTaskLog> util = new ExcelUtil<ScheduleTaskLog>(ScheduleTaskLog.class);
        return util.exportExcel(list, "调度日志");
    }

    @Log(title = "调度日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jobLogService.deleteJobLogByIds(ids));
    }

    @RequiresPermissions("monitor:job:detail")
    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") String jobLogId, ModelMap mmap)
    {
        String[] ts = jobLogId.split("\\|");
        long id = Long.parseLong(ts[0]);
        try {
            Date doneDate = sf.parse(ts[1]);
            mmap.put("name", "jobLog");
            mmap.put("jobLog", jobLogService.selectJobLogById(id,doneDate));
        }catch (Exception e){

        }

        return prefix + "/detail";
    }

    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}
