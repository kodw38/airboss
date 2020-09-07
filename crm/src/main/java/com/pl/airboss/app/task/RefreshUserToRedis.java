package com.pl.airboss.app.task;/**
 * Created by admin on 2020/7/14.
 */

import com.pl.airboss.app.bean.UserInfoBean;
import com.pl.airboss.app.bean.UserOrderBean;
import com.pl.airboss.app.dao.UserOrderBeanMapper;
import com.pl.airboss.framework.bean.CfgScheduleTaskBean;
import com.pl.airboss.framework.task.ITask;
import com.pl.airboss.framework.task.ITaskAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RefreshUserToRedis
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/7/14 17:45
 * @Version 1.0
 **/
@Component("RefreshUserToRedis")
public class RefreshUserToRedis implements ITask {

    @Autowired
    RedisTemplate orderRedis;

    @Autowired
    UserOrderBeanMapper orderBeanMapper;

    CfgScheduleTaskBean bean;


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

    }

    @Override
    public void run() {
        //clean redis
        //orderRedis.opsForCluster().flushDb();
        int count = orderBeanMapper.countCacheData();
        int each = 10000;
        int times = count/each+1;
        int start=0;
        int end=each;
        for(int i=0;i<times;i++) {
            List<Map> ls = orderBeanMapper.selectCacheData(start,each);
            if (null != ls) {
                for(Map m:ls){
                    add(m);
                }
            }
            start=end;
        }

    }

    public void add(Map m){
        orderRedis.opsForSet().add("user_order_sms",getKey((String)m.get("SP_CODE"),(String)m.get("BUSINESS"),(String)m.get("BILL_ID")));
    }
    public void add(UserInfoBean user, UserOrderBean order){
        orderRedis.opsForSet().add("user_order_sms",getKey(user.getSpCode(),order.getBusiness(),order.getBillId()));
    }
    public void delete(String sp,String business,String billId){
        orderRedis.delete(getKey(sp,business,billId));
    }

    String getValue(UserInfoBean user,UserOrderBean orderBean){
        return getValue2(user.getSpCode(),user.getBillId(),orderBean.getBusiness());
    }
    String getValue2(String sp,String business,String billId){
        return ("{\"SpCode\":\""+sp+"\","+
                 "\"Business\":\""+business+"\","+
                 "\"BillId\":\""+billId+"\"}");
    }
    String getKey(String sp,String busi,String billId){
        //return sp+"_"+busi+"_"+billId;
        return billId;
    }


}
