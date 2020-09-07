package com.pl.airboss.app.cache;/**
 * Created by admin on 2020/4/26.
 */

import com.pl.airboss.app.dao.CfgResPriceBeanMapper;
import com.pl.airboss.framework.task.ITaskAction;
import com.pl.airboss.app.bean.CfgResPriceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CacheResPrice
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/4/26 14:39
 * @Version 1.0
 **/
@Component("com.pl.airboss.apps.cache.CacheResPrice")
public class CacheResPrice implements ITaskAction {
    boolean init;

    @Autowired
    CfgResPriceBeanMapper priceBeanMapper;

    @Override
    public Object doTask() {
        List<CfgResPriceBean> all = priceBeanMapper.selectAll();
        Map ret = new HashMap();
        if(null != all){
            for(CfgResPriceBean b:all){
                ret.put(b.getSrcSp(),b);
            }
        }
        init=true;
        return ret;
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
