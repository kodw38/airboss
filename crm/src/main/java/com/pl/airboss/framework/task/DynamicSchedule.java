package com.pl.airboss.framework.task;

import com.pl.airboss.framework.bean.CfgScheduleTaskBean;
import com.pl.airboss.utils.SpringUtils;
import com.pl.airboss.framework.cache.impl.CacheManager;
import com.pl.airboss.framework.dao.CfgScheduleTaskBeanMapper;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfiguration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName DynamicScheduleTask
 * @Description 从表CFG_SCHEDULE_TASK中读取数据，根据Cron表达式定时执行任务
 * @Author Kod Wong
 * @Date 2020/4/25 23:33
 * @Version 1.0
 **/

@SuppressWarnings("unchecked")
@Component
@EnableScheduling
public class DynamicSchedule implements SchedulingConfigurer /*, ApplicationContextAware */{

    static List<ITask> extenalTasks= new LinkedList();

    static transient Logger log = LoggerFactory.getLogger(DynamicSchedule.class);
    //ApplicationContext applicationContext;

    @Autowired
    CfgScheduleTaskBeanMapper scheduleTaskBeanMapper;

    /*public <T> T getBean(String beanName) {
        if (applicationContext.containsBean(beanName)) {
            return (T) applicationContext.getBean(beanName);
        } else {
            return null;
        }
    }*/

    @Autowired
    SchedulingConfiguration schedulingConfiguration;

    public boolean isValidCron(String cron){
        return true;
    }
    /** 手动执行一次任务
     *@Description
     *@auther Kod Wong
     *@Date 2020/5/16 22:28
     *@Param
     *@return
     *@Version 1.0
     */
    public boolean doTask(Integer id){
        Set<ScheduledTask> ls = schedulingConfiguration.scheduledAnnotationProcessor().getScheduledTasks();
        if(null !=ls) {
            for (ScheduledTask t : ls) {
                if (t.getTask().getRunnable() instanceof ITask) {
                    if(((ITask)t.getTask().getRunnable()).getBean().getRecId().equals(id) ){
                        if( t.getTask() instanceof CronTask) {
                            t.getTask().getRunnable().run();
                        }else{
                            t.getTask().getRunnable().run();
                        }
                    }
                }
            }
        }
        for(ITask t:extenalTasks){
            if(t.getBean().getRecId().equals(id) ){
               t.run();
            }
        }
        return false;
    }
    public void suspendTask(Integer id){
        Set<ScheduledTask> ls = schedulingConfiguration.scheduledAnnotationProcessor().getScheduledTasks();
        if(null !=ls) {
            for (ScheduledTask t : ls) {
                if (t.getTask().getRunnable() instanceof ITask) {
                    if(((ITask)t.getTask().getRunnable()).getBean().getRecId().equals(id) && t.getTask() instanceof CronTask){
                        t.cancel();
                    }
                }
            }
        }
    }
    public void activeTask(Integer id){
        Set<ScheduledTask> ls = schedulingConfiguration.scheduledAnnotationProcessor().getScheduledTasks();
        if(null !=ls) {
            for (ScheduledTask t : ls) {
                if (t.getTask().getRunnable() instanceof ITask) {
                    if(((ITask)t.getTask().getRunnable()).getBean().getRecId().equals(id) && t.getTask() instanceof CronTask){
                        t.cancel();
                    }
                }
            }
        }
    }
    public void updateCron(Integer id,String cron){
        Set<ScheduledTask> ls = schedulingConfiguration.scheduledAnnotationProcessor().getScheduledTasks();
        if(null !=ls) {
            for (ScheduledTask t : ls) {
                if(t.getTask().getRunnable() instanceof ITask){
                    if(((ITask)t.getTask().getRunnable()).getBean().getRecId().equals(id) && t.getTask() instanceof CronTask){
                        if(!((CronTask)t.getTask()).getExpression().equalsIgnoreCase(cron)){
                            CronTrigger trigger = new CronTrigger(cron);
                            try {
                                //Field f = CronTask.class.getField("expression");
                                //f.setAccessible(true);
                                //f.set((CronTask) t.getTask(), cron);
                                /*Field f = CronTask.class.getField("trigger");
                                f.setAccessible(true);
                                f.set((CronTask) t.getTask(), trigger);*/
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        }
    }

    public boolean deleteTask(String className){
        if(null != schedulingConfiguration) {
            Set<ScheduledTask> ls = schedulingConfiguration.scheduledAnnotationProcessor().getScheduledTasks();
            if(null !=ls){
                for(ScheduledTask t:ls){
                    if(t.getTask().getRunnable().equals(className)){
                        log.info("cancel task "+className);
                        t.cancel();
                        //记录日志
                        log.info("finished cancel "+className);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public <T> T getObject(String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        T t = SpringUtils.getBean(beanName);
        if(null != t){
            return t;
        }else{
            return (T) this.getClass().getClassLoader().loadClass(beanName).newInstance();
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar){
        List<CfgScheduleTaskBean> ls = scheduleTaskBeanMapper.selectAll();
        if(null != ls && ls.size()>0){
            for(CfgScheduleTaskBean bean:ls){
                if(StringUtils.isNotBlank(bean.getCron()) && StringUtils.isNotBlank(bean.getTaskClass())){
                    try {
                        Runnable task=null;
                        //如果有配置管理类，实例化管理类，并定时执行管理类，任务类实例化后交给管理类处理。
                        if(StringUtils.isNotBlank(bean.getManageClass()) && StringUtils.isNotBlank(bean.getTaskClass())){
                            Object m = getObject(bean.getManageClass());
                            if(null !=m && m instanceof ITask) {
                                ITask tm = (ITask) m;
                                tm.setBean(bean);
                                Object a = getObject(bean.getTaskClass());
                                if(null!=a && a instanceof ITaskAction) {
                                    tm.setTaskAction((ITaskAction)a);
                                    CronTask t = new CronTask(tm, bean.getCron());
                                    scheduledTaskRegistrar.addCronTask(t);

                                }else{
                                    new Exception("please implement  "+ITask.class.getName()+" "+bean.getTaskClass());
                                }
                            }else{
                                new Exception("please implement  "+ITask.class.getName()+" "+bean.getManageClass());
                            }
                        }else if(StringUtils.isNotBlank(bean.getTaskClass()) && StringUtils.isNotBlank(bean.getTaskType())&&"cache".equalsIgnoreCase(bean.getTaskType())){
                            //如果是固定的cache类型，默认管理类是CacheManager
                            CacheManager cm = SpringUtils.createBean(CacheManager.class);
                            cm.setBean(bean);
                            Object a = getObject(bean.getTaskClass());
                            if(a instanceof ITaskAction) {
                                cm.setTaskAction((ITaskAction) a);
                                CronTask t = new CronTask(cm, bean.getCron());
                                scheduledTaskRegistrar.addCronTask(t);
                            }else{
                                new Exception("please implement  "+ITask.class.getName()+" "+bean.getTaskClass());
                            }
                        }else if(StringUtils.isNotBlank(bean.getManageClass())){
                            //如果没有管理类，当做普通的任务执行，只要task实现了Runnable接口
                            Object m = getObject(bean.getManageClass());
                            if(m instanceof ITask) {
                                ITask t = (ITask)m;
                                t.setBean(bean);
                                scheduledTaskRegistrar.addCronTask(t, bean.getCron());
                            }else{
                                new Exception("please implement Runnable "+bean.getTaskClass());
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        log.error("can not load schedule task , must implement Runnable",e);
                    } catch (IllegalAccessException e) {
                        log.error("can not load schedule task , must implement Runnable",e);
                    } catch (InstantiationException e) {
                        log.error("can not load schedule task , must implement Runnable",e);
                    }

                }else if(StringUtils.isNotBlank(bean.getManageClass())){
                    try {
                        Object m = getObject(bean.getManageClass());
                        if (m instanceof ITask) {
                            ITask t = (ITask) m;
                            t.setBean(bean);
                            addExtenalTask(t);
                        } else {
                            new Exception("please implement Runnable " + bean.getTaskClass());
                        }
                    }catch (Exception e){

                    }
                }
            }
        }

    }

    void addExtenalTask(ITask task){
        extenalTasks.add(task);
    }

    /*@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }*/
}
