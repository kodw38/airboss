package com.pl.airboss.web.cache;/**
 * Created by admin on 2020/4/26.
 */

import com.pl.airboss.framework.task.ITaskAction;
import com.pl.airboss.web.bean.SysDictData;
import com.pl.airboss.web.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName CacheStaticData
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/4/26 14:56
 * @Version 1.0
 **/
@Component("com.pl.airboss.web.cache.CacheStaticData")
public class CacheStaticData implements ITaskAction {
    boolean init;

    @Autowired
    @Qualifier("sysDictDataServiceImpl")
    ISysDictDataService dictDataService;

    @Override
    public Object doTask() {
        HashMap map = new HashMap();
        SysDictData d = new SysDictData();
        d.setStatus("1");
        List<SysDictData> all =dictDataService.selectDictDataList(d);
        if(null != all) {
            for (SysDictData b : all) {
                if(map.containsKey(b.getDictType())){
                    ((List)map.get(b.getDictType())).add(b);
                }else{
                    List li = new LinkedList();
                    li.add(b);
                    map.put(b.getDictType(),li);
                }

            }
        }
        init=true;
        return map;
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
