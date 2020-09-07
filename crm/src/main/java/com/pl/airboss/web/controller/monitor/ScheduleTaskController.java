package com.pl.airboss.web.controller.monitor;

import com.pl.airboss.framework.annotation.Log;
import com.pl.airboss.framework.bean.BusinessType;
import com.pl.airboss.framework.bean.CfgScheduleTaskBean;
import com.pl.airboss.framework.dao.CfgScheduleTaskBeanMapper;
import com.pl.airboss.framework.task.DynamicSchedule;
import com.pl.airboss.web.controller.BaseController;
import com.pl.airboss.web.utils.AjaxResult;
import com.pl.airboss.web.utils.ExcelUtil;
import com.pl.airboss.web.utils.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度任务信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/job")
public class ScheduleTaskController extends BaseController
{
    private String prefix = "monitor/job";

    @Autowired
    private CfgScheduleTaskBeanMapper jobService;

    @Autowired
    DynamicSchedule schedule;

    @RequiresPermissions("monitor:job:view")
    @GetMapping()
    public String job()
    {
        return prefix + "/job";
    }

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CfgScheduleTaskBean job)
    {
        startPage();
        List<CfgScheduleTaskBean> list = jobService.selectList(job);
        return getDataTable(list);
    }

    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CfgScheduleTaskBean job)
    {
        List<CfgScheduleTaskBean> list = jobService.selectList(job);
        ExcelUtil<CfgScheduleTaskBean> util = new ExcelUtil<CfgScheduleTaskBean>(CfgScheduleTaskBean.class);
        return util.exportExcel(list, "定时任务");
    }

    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer ids)
    {
        CfgScheduleTaskBean b = jobService.selectByPrimaryKey(ids);
        jobService.deleteByPrimaryKey(ids);
        schedule.deleteTask(b.getManageClass());
        return success();
    }

    @RequiresPermissions("monitor:job:detail")
    @GetMapping("/detail/{recId}")
    public String detail(@PathVariable("recId") Integer jobId, ModelMap mmap)
    {
        mmap.put("name", "job");
        mmap.put("job", jobService.selectByPrimaryKey(jobId));
        return prefix + "/detail";
    }

    /**
     * 任务调度状态修改
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(CfgScheduleTaskBean job)
    {
        CfgScheduleTaskBean newJob = jobService.selectByPrimaryKey(job.getRecId());
        newJob.setState(job.getState());
        int ret = jobService.updateByPrimaryKeySelective(newJob);
        schedule.suspendTask(job.getRecId());
        return toAjax(ret);
    }

    /**
     * 任务调度立即执行一次
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(CfgScheduleTaskBean job)
    {
        schedule.doTask(job.getRecId());
        return success();
    }

    /**
     * 新增调度
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @RequiresPermissions("monitor:job:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated CfgScheduleTaskBean job)
    {
        return toAjax(jobService.insert(job));
    }

    /**
     * 修改调度
     */
    @GetMapping("/edit/{recId}")
    public String edit(@PathVariable("recId") Integer jobId, ModelMap mmap)
    {
        mmap.put("job", jobService.selectByPrimaryKey(jobId));
        return prefix + "/edit";
    }

    /**
     * 修改保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated CfgScheduleTaskBean job)
    {
        int ret = jobService.updateByPrimaryKeySelective(job);
        schedule.updateCron(job.getRecId(),job.getCron());
        return toAjax(ret);
    }

    /**
     * 校验cron表达式是否有效
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public boolean checkCronExpressionIsValid(CfgScheduleTaskBean job)
    {
        return schedule.isValidCron(job.getCron());
    }
}
