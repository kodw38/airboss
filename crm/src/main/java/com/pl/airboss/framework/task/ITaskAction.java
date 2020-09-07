package com.pl.airboss.framework.task;/**
 * Created by admin on 2020/4/26.
 */

/**
 * @ClassName ITaskAction
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/4/26 11:14
 * @Version 1.0
 **/
public interface ITaskAction {
    public Object doTask();
    boolean isInit();
    void setInit(boolean b);
}
