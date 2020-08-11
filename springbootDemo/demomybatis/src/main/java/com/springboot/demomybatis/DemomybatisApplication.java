package com.springboot.demomybatis;

import com.springboot.demomybatis.dao.MyBatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages = "com.springboot.demomybatis")
@MapperScan("com.springboot.demomybatis.dao")
public class DemomybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomybatisApplication.class, args);
    }

    /**
     * 使用MapperFactoryBean装配Mybatis接口
     *//*
    @Autowired
    SqlSessionFactory sqlSessionFactory=null;
    @Bean
    public MapperFactoryBean<MyBatisUserDao> initMtBatisUserDao(){
        MapperFactoryBean<MyBatisUserDao> bean=new MapperFactoryBean<>();
        bean.setMapperInterface(MyBatisUserDao.class);
        bean.setSqlSessionFactory(sqlSessionFactory);
        return bean;
    }*/
}
