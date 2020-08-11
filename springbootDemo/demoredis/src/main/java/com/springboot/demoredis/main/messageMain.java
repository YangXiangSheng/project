package com.springboot.demoredis.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages = "com.springboot.demoredis")
@MapperScan(basePackages = "com.springboot.demoredis",annotationClass = Repository.class)
@EnableCaching
public class messageMain {
    //RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate=null;
    //Redis连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory=null;
    //Redis消息监听器
    @Autowired
    private MessageListener redisMsgListener=null;
    //任务池
    private ThreadPoolTaskScheduler taskScheduler=null;
    /**
     * 创建任务池，运行线程等待处理Redis的消息
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler(){
        if(taskScheduler!=null){
            return taskScheduler;
        }
        taskScheduler=new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }
    /**
     * 定义Redis监听容器
     * @return 监听容器
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container=new RedisMessageListenerContainer();
        //Redis连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        //定义监听渠道，名称为topic1
        Topic topic=new ChannelTopic("topic1");
        //使用监听器监听Redis的消息
        container.addMessageListener(redisMsgListener,topic);
        return container;
    }
    public static void main(String[] args) {
        SpringApplication.run(messageMain.class, args);
    }
}
