package com.pl.airboss.framework.aspectj;/**
 * Created by admin on 2020/5/18.
 */

import com.pl.airboss.framework.bean.ScheduleTaskLog;
import com.pl.airboss.framework.task.ITask;
import com.pl.airboss.web.dao.ScheduleTaskLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName ScheduleLogAspect
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/18 16:28
 * @Version 1.0
 **/
@Aspect
@Component
public class ScheduleLogAspect {

    @Autowired
    ScheduleTaskLogMapper taskLogMapper;

    ThreadLocal l = new ThreadLocal();

    @Pointcut("execution(* com.pl.airboss.framework.task.ITask.run(..))")
    public void scheduleLogPointcut(){

    }

    @Before("scheduleLogPointcut()")
    public void doBefore(){
        l.set(new Date());
    }

    @AfterReturning(pointcut = "scheduleLogPointcut()", returning = "jsonResult")
    public void doAfter(JoinPoint joinPoint, Object jsonResult){

        Object o = joinPoint.getThis();
        if(null != o && o instanceof ITask){
            ScheduleTaskLog log = new ScheduleTaskLog();
            log.setEndTime(new Date());
            log.setInvokeTarget(o.getClass().getName());
            log.setJobMessage(((ITask) o).getBean().getTaskCode()+"|"+((ITask) o).getBean().getCron());
            log.setJobName(((ITask) o).getBean().getTaskCode());
            log.setStartTime((Date)l.get());
            log.setStatus("1");
            log.setCreateTime(new Date());
            taskLogMapper.insertJobLog(log );
        }
    }
    @AfterThrowing(value = "scheduleLogPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e){
        Object o = joinPoint.getThis();
        if(null != o && o instanceof ITask) {
            ScheduleTaskLog log = new ScheduleTaskLog();
            log.setEndTime(new Date());
            log.setInvokeTarget(o.getClass().getName());
            log.setJobMessage(((ITask) o).getBean().getTaskCode() + "|" + ((ITask) o).getBean().getCron());
            log.setJobName(((ITask) o).getBean().getTaskCode());
            log.setStartTime((Date) l.get());
            log.setStatus("0");
            log.setExceptionInfo(e.getMessage());
            log.setCreateTime(new Date());
            taskLogMapper.insertJobLog(log);
        }
    }
}
