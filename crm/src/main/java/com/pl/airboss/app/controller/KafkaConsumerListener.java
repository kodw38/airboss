package com.pl.airboss.app.controller;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2020/4/12.
 */
@Component
@EnableKafka
public class KafkaConsumerListener {

    AtomicInteger c = new AtomicInteger(0);
    @PostConstruct
    public void init(){
        createTopic(new String[]{"SatelliteSMS","CMCMobileSMS"});
    }
    private void createTopic(String[] topics){
        /*ZkUtils zkUtils = ZkUtils.apply("", 30000, 30000, JaasUtils.isZkSecurityEnabled());
        for(String topic:topics) {
            KafkaTopicBean top = new KafkaTopicBean();
            topic.setTopicName(topic);  //topic名称
            topic.setPartition(4);            //分区数量设置为1
            topic.setReplication(4);       //副本数量设置为1
            AdminUtils.createTopic(zkUtils, topic.getTopicName(), topic.getPartition(), topic.getReplication(), new Properties(), new RackAwareMode.Enforced$());
        }
        zkUtils.close();*/
    }

    @KafkaListener(topics = {"SatelliteSMS"})
    public void SmsFromSatellitelistener(String data){

        c.incrementAndGet();

        //if(c.intValue()==100000){
            System.out.println("date finished:"+System.currentTimeMillis());
        //}
    }

    @KafkaListener(topics = {"CMCMobileSMS"})
    public void SmsCMCMobilelistener(String data){

    }
}
