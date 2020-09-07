package com.pl.airboss.app.cache;/**
 * Created by admin on 2020/4/26.
 */

import com.pl.airboss.app.bean.CfgSensitiveBean;
import com.pl.airboss.app.dao.CfgSensitiveBeanMapper;
import com.pl.airboss.framework.task.ITaskAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ITask
 * @Description Cache all sensitive word from table cfg_sensitive_words
 * @Author Kod Wong
 * @Date 2020/4/26 9:37
 * @Version 1.0
 **/
@Component("com.pl.airboss.apps.cache.CacheSensitiveWords")
public class CacheSensitiveWords implements ITaskAction {

    @Autowired
    CfgSensitiveBeanMapper sensitiveBeanMapper ;
    boolean isInit=false;

    @Override
    public Object doTask() {
        List ret = new ArrayList();
        if(null != sensitiveBeanMapper){
            List<CfgSensitiveBean> all = sensitiveBeanMapper.selectAll();
            if(null != all){
                for(CfgSensitiveBean b:all){
                    if(null != b.getContent())
                        ret.add(b.getContent());
                }
            }
        }
        isInit=true;
        return ret;
    }

    @Override
    public boolean isInit() {
        return isInit;
    }

    @Override
    public void setInit(boolean b) {
        isInit=b;
    }
}
