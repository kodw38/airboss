package com.pl.airboss.app.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.pl.airboss.app.utils.KafkaClient;
import com.pl.airboss.app.utils.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by admin on 2020/4/12.
 */
@Configuration
@ConfigurationProperties(
        prefix = "spring"
)
@MapperScan("com.pl.**.dao")
@ComponentScan("com.pl")
public class AppConfiguration {
    Boolean enableKafka;
    @Bean
    public StringUtils getStringUtils(){
        return new StringUtils();
    }
    @Bean
    public KafkaClient getKafkaClient(){
        return new KafkaClient();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDruidDataSource(){
        return new DruidDataSource();
    }

    @Bean("org.springframework.kafka.config.internalKafkaListenerEndpointRegistry")
    public KafkaListenerEndpointRegistry getKafkaListenerEndpointRegistry(Map<String,Object> map){
        return new KafkaListenerEndpointRegistry() {
            public boolean isAutoStartup() {
                return enableKafka;
            }
        };
    }

    public Boolean getEnableKafka() {
        return enableKafka;
    }

    public void setEnableKafka(Boolean enableKafka) {
        this.enableKafka = enableKafka;
    }


}
