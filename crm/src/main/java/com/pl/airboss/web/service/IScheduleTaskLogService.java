package com.pl.airboss.web.service;

import com.pl.airboss.framework.bean.ScheduleTaskLog;

import java.util.Date;
import java.util.List;

/**
 * 定时任务调度日志信息信息 服务层
 * 
 * @author ruoyi
 */
public interface IScheduleTaskLogService
{
    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    public List<ScheduleTaskLog> selectJobLogList(ScheduleTaskLog jobLog);

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    public ScheduleTaskLog selectJobLogById(Long jobLogId,Date createDate);

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    public void addJobLog(ScheduleTaskLog jobLog);

    /**
     * 批量删除调度日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobLogByIds(String ids);

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     * @return 结果
     */
    public int deleteJobLogById(Long jobId);
    
    /**
     * 清空任务日志
     */
    public void cleanJobLog();
}
