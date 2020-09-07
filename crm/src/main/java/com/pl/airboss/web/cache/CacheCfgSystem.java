package com.pl.airboss.web.cache;/**
 * Created by admin on 2020/5/6.
 */

import com.pl.airboss.framework.task.ITaskAction;
import com.pl.airboss.web.bean.SysConfig;
import com.pl.airboss.web.dao.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CacheCfgSystem
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/6 10:30
 * @Version 1.0
 **/
@Component("com.pl.airboss.web.cache.CacheCfgSystem")
public class CacheCfgSystem implements ITaskAction {
    boolean isinit=false;
    @Autowired
    private SysConfigMapper configMapper;

    @Override
    public Object doTask() {
        Map ret = new HashMap();
        List<SysConfig> ls = configMapper.selectAll();
        if(null != ls){
            for(SysConfig l:ls){
                ret.put(l.getConfigKey(),l);
            }
        }
        isinit=true;
        return ret;

    }

    @Override
    public boolean isInit() {
        return isinit;
    }

    @Override
    public void setInit(boolean b) {
        isinit=b;
    }
}
