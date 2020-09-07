package com.pl.airboss.app.cache;/**
 * Created by admin on 2020/4/26.
 */

import com.pl.airboss.app.bean.CfgBlackWhiteBean;
import com.pl.airboss.app.dao.CfgBlackWhiteBeanMapper;
import com.pl.airboss.framework.task.ITaskAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName CacheBlackWhiteList
 * @Description 缓存所有的黑白名单
 * @Author Kod Wong
 * @Date 2020/4/26 14:18
 * @Version 1.0
 **/
@Component("com.pl.airboss.apps.cache.CacheBlackWhiteList")
public class CacheBlackWhiteList implements ITaskAction {

    @Autowired
    CfgBlackWhiteBeanMapper blackWhiteBeanMapper ;
    boolean isInit=false;

    @Override
    public Object doTask() {
        HashMap map = new HashMap();
        List<CfgBlackWhiteBean> all = blackWhiteBeanMapper.selectAll();
        if(null != all){
            for(CfgBlackWhiteBean b:all){
                map.put(b.getBillId(),b);
            }
        }
        isInit=true;
        return map;
    }

    @Override
    public boolean isInit() {
        return isInit;
    }

    @Override
    public void setInit(boolean b) {
        this.isInit=b;
    }
}
