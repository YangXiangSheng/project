package com.springboot.demoredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.springboot.demoredis")
@MapperScan(basePackages = "com.springboot.demoredis",annotationClass = Repository.class)
@EnableCaching
public class DemoredisApplication {
    // RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private RedisConnectionFactory connectionFactory = null;
    // 设置RedisTemplate的序列化器
    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    // 自定义初始化方法
    @PostConstruct
    public void init() {
        initRedisTemplate();
    }
    /*@Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 1.创建 redisTemplate 模版
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 2.关联 redisConnectionFactory
        template.setConnectionFactory(redisConnectionFactory);
        // 3.创建 序列化类
        GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
        // 6.序列化类，对象映射设置
        // 7.设置 value 的转化格式和 key 的转化格式
        template.setValueSerializer(genericToStringSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(DemoredisApplication.class, args);
    }
    /**
     * 自定义缓存管理器
     * @Bean(name = "redisCacheManager" )
     * 	public RedisCacheManager initRedisCacheManager() {
     * 		// Redis加锁的写入器
     * 		RedisCacheWriter writer= RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
     * 		// 启动Redis缓存的默认设置
     * 		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
     * 		// 设置JDK序列化器
     * 		config = config.serializeValuesWith(SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
     * 		// 禁用前缀
     * 		config = config.disableKeyPrefix();
     * 		//设置10分钟超时
     * 		config = config.entryTtl(Duration.ofMinutes(10));
     * 		// 创建缓Redis存管理器
     * 		RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
     * 		return redisCacheManager;
     *        }
     */

}
