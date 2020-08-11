package com.springboot.demoredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class RedisConfig {
    /*private RedisConnectionFactory connectionFactory=null;
    @Bean(name="redisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if(this.connectionFactory!=null){
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        //最大空闲数
        poolConfig.setMaxIdle(30);
        //最大连接数
        poolConfig.setMaxTotal(50);
        //最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000);
        //创建Jedis连接工厂
        JedisConnectionFactory connectionFactory=new JedisConnectionFactory(poolConfig);
        //获取单机的Redis配置
        RedisStandaloneConfiguration rsCfg =connectionFactory.getStandaloneConfiguration();
        rsCfg.setHostName("127.0.0.1");
        rsCfg.setPort(6379);
        rsCfg.setPassword("");
        this.connectionFactory=connectionFactory;
        return connectionFactory;

    }
    @Bean(name="redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){
        RedisTemplate<Object,Object> redisTemplate=new RedisTemplate<>();
        //RedisTemplate会自动初始化StringRedisSerializer,所以这里直接获取
        RedisSerializer stringRedisSerializer=redisTemplate.getStringSerializer();
        //设置字符串序列化器，这样Spring就会把Redis的key当做字符串处理
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }*/
}
