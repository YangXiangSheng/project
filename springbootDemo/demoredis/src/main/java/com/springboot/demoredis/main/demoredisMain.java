package com.springboot.demoredis.main;

import com.springboot.demoredis.config.RedisConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class demoredisMain {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
	redisTemplate.opsForValue().set("key1", "value1");
		redisTemplate.opsForHash().put("hash", "field", "hvalue");
       // useSessionCallback(redisTemplate);
//		useRedisCallback(redisTemplate);
       // ctx.close();
    }
}
