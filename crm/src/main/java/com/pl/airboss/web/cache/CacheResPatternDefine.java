package com.pl.airboss.web.cache;/**
 * Created by admin on 2020/9/9.
 */

import com.pl.airboss.crm.res.bean.ResPatternDefineBean;
import com.pl.airboss.crm.res.dao.ResPatternDefineBeanMapper;
import com.pl.airboss.framework.task.ITaskAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName CacheResPatternDefine
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/9/9 16:07
 * @Version 1.0
 **/
@Component("com.pl.airboss.web.cache.CacheResPatternDefine")
public class CacheResPatternDefine implements ITaskAction {
    boolean init;

    @Autowired
    ResPatternDefineBeanMapper resPatternDefineBeanMapper;

    @Override
    public Object doTask() {
        ResPatternDefineBean d = new ResPatternDefineBean();
        d.setState("1");
        List<ResPatternDefineBean> all =resPatternDefineBeanMapper.selectList(d);

        init=true;
        return all;
    }


    @Override
    public boolean isInit() {
        return init;
    }

    @Override
    public void setInit(boolean b) {
        init=b;
    }
}
