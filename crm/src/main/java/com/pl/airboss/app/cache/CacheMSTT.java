package com.pl.airboss.app.cache;/**
 * Created by admin on 2020/4/26.
 */

import com.pl.airboss.framework.task.ITaskAction;
import com.pl.airboss.app.bean.CfgMSTTBean;
import com.pl.airboss.app.dao.CfgMSTTBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CacheMSTT
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/4/26 14:31
 * @Version 1.0
 **/
@Component("com.pl.airboss.apps.cache.CacheMSTT")
public class CacheMSTT implements ITaskAction {
    boolean isinit;

    @Autowired
    CfgMSTTBeanMapper msttBeanMapper;

    @Override
    public Object doTask() {
        List<CfgMSTTBean> all = msttBeanMapper.selectAll();
        Map map = new HashMap();
        if(null != all){
            for(CfgMSTTBean b:all){
                map.put(b.getTtCode(),b);
            }
        }
        isinit=true;
        return map;
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
