package com.pl.airboss.framework.cache.impl;/**
 * Created by admin on 2020/4/26.
 */

import com.pl.airboss.framework.bean.CfgScheduleTaskBean;
import com.pl.airboss.framework.task.ITaskAction;
import com.pl.airboss.framework.task.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ClassName CacheManager
 * @Description 定时执行task的任务，结果缓存到CacheMap中
 * @Author Kod Wong
 * @Date 2020/4/26 9:11
 * @Version 1.0
 **/
@Component("CacheManager")
public class CacheManager  implements ITask {
    static transient Logger log = LoggerFactory.getLogger(CacheManager.class);
    static ConcurrentMap CacheMap = new ConcurrentHashMap();
    static ConcurrentMap CacheTask = new ConcurrentHashMap();
    ITaskAction taskAction=null;
    CfgScheduleTaskBean bean=null;


    @Override
    public void setBean(CfgScheduleTaskBean bean) {
        this.bean=bean;
    }

    @Override
    public CfgScheduleTaskBean getBean() {
        return bean;
    }

    @Override
    public void setTaskAction(ITaskAction action) {
        this.taskAction=action;
        if(null != bean && bean.getIsAppInitRun()==1){
            Object o = action.doTask();
            if(null != o)
                CacheMap.put(action.getClass().getName(),o);
            log.info("finish load cache "+action.getClass().getName());
        }
        CacheTask.put(action.getClass().getName(),action);
    }

    @Override
    public void run() {
        if(null != taskAction){
            Object o = taskAction.doTask();
            if(null != o){
                CacheMap.put(taskAction.getClass().getName(),o);
            }
        }
    }
    /**
     *@Description  获取某个缓存的所有数据
     *@auther Kod Wong
     *@Date 2020/4/26 9:21
     *@Param
     *@return
     *@Version 1.0
     */
    public Object getCache(String cacheClazzName){
        if(null != CacheMap){
            Object o = CacheMap.get(cacheClazzName);
            if(o==null){
                ITaskAction ta = (ITaskAction) CacheTask.get(cacheClazzName);
                if(null != ta){
                    if(!ta.isInit()) {
                        Object data = ta.doTask();
                        CacheMap.put(cacheClazzName,data);
                        return data;
                    }
                }
            }else{
                return o;
            }

        }
        return null;
    }

    /**
     *@Description 如果缓存的是个Map对象，可以根据传入的key，获取value
     *@auther Kod Wong
     *@Date 2020/4/26 9:24
     *@Param
     *@return
     *@Version 1.0
     */
    public Object getMapCacheValue(String cacheClassName,String key){
        Object o = getCache(cacheClassName);
        if(null != o && o instanceof Map){
            return ((Map)o).get(key);
        }
        return null;
    }

    /**
     *@Description  方便业务对缓存的操作提供，如果缓存的是list，判断是否包含某个值
     *@auther Kod Wong
     *@Date 2020/4/26 9:45
     *@Param
     *@return
     *@Version 1.0
     */
    public boolean containsListCacheValue(String cacheClassName,String value){
        Object o = getCache(cacheClassName);
        if(null != o && o instanceof Collection){
            return ((Collection)o).contains(value);
        }
        return false;
    }

    /**
     *@Description 方便业务对缓存的操作提供，如果缓存的是list，判断其中是否有一条数据包含某个值
     *@auther Kod Wong
     *@Date 2020/4/26 9:49
     *@Param
     *@return
     *@Version 1.0
     */
    public boolean containsPartOfListCacheValue(String cacheClassName,String txt){
        Object o = getCache(cacheClassName);
        if(null != o && o instanceof Collection){
            Iterator it = ((Collection) o).iterator();
            while(it.hasNext()){
                if(txt.indexOf(it.next().toString())>=0){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean clear(String cacheClassName){
        Object o = CacheMap.remove(cacheClassName);
        ITaskAction ta = (ITaskAction) CacheTask.get(cacheClassName);
        ta.setInit(false);
        if(null != o)
            return true;
        else
            return false;
    }

}
