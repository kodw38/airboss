package com.pl.airboss.app.config;/**
 * Created by admin on 2020/7/14.
 */

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RedisMultClusterConfiguration
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/7/14 17:22
 * @Version 1.0
 **/
@Configuration
public class RedisMultClusterConfiguration {
    @Value("${spring.redis-order.cluster.nodes:}")
    String orderRedisNodes;


    @Value("${spring.redis-order.host:}")
    String orderRedisHost;
    @Value("${spring.redis-order.port:}")
    String orderRedisPort;
    @Value("${spring.redis-order.password:}")
    String orderRedisPasswrod;
    @Value("${spring.redis-order.database:}")
    String orderRedisDB;

    @Value("${spring.redis-other.cluster.nodes:}")
    String otherRedisNodes;


    @Bean(name = "orderRedis")
    public RedisTemplate redisClusterConfiguration() {
        if(null != orderRedisNodes && !"".equals(orderRedisNodes.trim())) {
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            //Set<RedisNode> clusterNodes
            String[] serverArray = orderRedisNodes.split(",");
            Set<RedisNode> nodes = new HashSet<RedisNode>();
            for (String ipPort : serverArray) {
                String[] ipAndPort = ipPort.split(":");
                nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
            }
            redisClusterConfiguration.setClusterNodes(nodes);
            //集群模式
            JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration, new JedisPoolConfig());
            factory.setUsePool(true);
            //实例化redistemplate
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(factory);
            return redisTemplate;
        }else if(StringUtils.isNotBlank(orderRedisHost) && StringUtils.isNotBlank(orderRedisPort)){
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
            config.setHostName(orderRedisHost);
            config.setPort(Integer.parseInt(orderRedisPort));
            if(null != orderRedisDB)
                config.setDatabase(Integer.parseInt(orderRedisDB));
            else
                config.setDatabase(2);
            if(StringUtils.isNotBlank(orderRedisPasswrod))
                config.setPassword(orderRedisPasswrod);
            JedisConnectionFactory factory = new JedisConnectionFactory(config);
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(factory);
            return redisTemplate;
        }else{
            return null;
        }

    }

    /**
     * Redis集群的配置
     *
     * @return RedisClusterConfiguration
     * @throws
     */
    @Bean(name = "otherRedis")
    public RedisTemplate redisClusterConfiguration2() {
        if(null != otherRedisNodes && !"".equals(otherRedisNodes.trim())) {
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            //Set<RedisNode> clusterNodes
            String[] serverArray = otherRedisNodes.split(",");
            Set<RedisNode> nodes = new HashSet<RedisNode>();
            for (String ipPort : serverArray) {
                String[] ipAndPort = ipPort.split(":");
                nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
            }
            redisClusterConfiguration.setClusterNodes(nodes);
            //集群模式
            JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration, new JedisPoolConfig());
            factory.setUsePool(true);
            //实例化redistemplate
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(factory);
            return redisTemplate;
        }else{
            return null;
        }
    }

}
