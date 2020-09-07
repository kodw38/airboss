package com.pl.airboss.framework.task;

import com.pl.airboss.framework.bean.CfgScheduleTaskBean;


/**
 * Created by admin on 2020/4/26.
 */
public interface ITask  extends Runnable{
    public void setBean(CfgScheduleTaskBean bean);
    public CfgScheduleTaskBean getBean();
    public void setTaskAction(ITaskAction action);
}
