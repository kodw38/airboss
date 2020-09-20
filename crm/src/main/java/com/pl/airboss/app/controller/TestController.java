package com.pl.airboss.app.controller;

import com.pl.airboss.app.config.AppConfiguration;
import com.pl.airboss.app.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.websocket.server.PathParam;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2020/4/12.
 */
@RestController
public class TestController {

    @Autowired
    StringRedisTemplate redisTemplate;
    ValueOperations<String, String> stringRedis;


    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    DataSource dataSource;


    @PostConstruct
    public void init(){
        stringRedis=redisTemplate.opsForValue();
    }


    @Autowired
    AppConfiguration beans ;

    @RequestMapping("/hello")
    public String getName(){
        stringRedis.set("name", "丁洁");
        return stringRedis.get("name");
    }

    @RequestMapping("/age")
    public boolean getAge(){
        return beans.getStringUtils().isContains("sdfsdf",new String[]{"fs","dd"});
    }

    @GetMapping("/send/{messge}")
    public String send(@PathVariable String messge){
        kafkaTemplate.send("SatelliteSMS",messge);
        return messge;
    }



    @RequestMapping("/start")
    public String start(){
        String[] keys=null;
        try {
            List li = new ArrayList();
            BufferedReader fr = new BufferedReader(new FileReader(new File("c:/logs/searchKeys.txt")));
            String line;
            while ((line = fr.readLine()) != null) {
                li.add(line);
            }
            fr.close();
            keys=(String[])li.toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("date start:"+System.currentTimeMillis());
        long l = System.currentTimeMillis();
        //for(int i=0;i<100000;i++){
            String s="Consumer Group （CG）：这是kafka用来实现一个topic消息的广播（发给所有的consumer）和单播（发给任意一个consumer）的手段。一个topic可以有多个CG。topic的消息会复制（不是真的复制，是概念上的）到所有的CG，但每个partion只会把消息发给该CG中的一个consumer。如果需要实现广播，只要每个consumer有一个独立的CG就可以了。要实现单播只要所有的consumer在同一个CG。用CG还可以将consumer进行自由的分组而不需要多次发送消息到不同的topic；";
            beans.getStringUtils().isContains(s,keys);
            kafkaTemplate.send("SatelliteSMS",s);
        //}
        return "finished "+((System.currentTimeMillis()-l)/1000) +" s";
    }

    @RequestMapping("/getdata")
    public String getDatafromdb(){
        Connection connection=null;
        try {
            connection = dataSource.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("select * from isp_user_info");
            while(rs.next()){
                System.out.println(rs.getString("ACCT_LOGIN"));
            }
        } catch (SQLException e) {
            if(null != connection) try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "";
    }

    /*@GetMapping("/blackwhite/add")
    public String addBlackWhite(CfgBlackWhiteBean bean)throws Exception{
        if(null != bean) {
            cfgBlackWhiteBeanMapper.insert(bean);
            return bean.getBillId();
        }else{
            throw new Exception("create Black_White error");
        }
    }
    @GetMapping("/blackwhite/get")
    public CfgBlackWhiteBean getCfgBlackWhiteBean(@PathParam("billId") String billId)throws Exception{
        return cfgBlackWhiteBeanMapper.selectByPrimaryKey(billId);
    }*/

    @Autowired
    CommonService cs;



    @GetMapping("/api/static")
    public List<Map> getStaticList(@PathParam("codeType") String codeType){
        return cs.getStaticByCodeType(codeType);
    }
}
