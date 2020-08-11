package com.springboot.transactional.demo.service.impl;

import com.springboot.transactional.demo.dao.UserDao;
import com.springboot.transactional.demo.pojo.User;
import com.springboot.transactional.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao=null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
/*
解决自调用失效
@Service
public class UserServiceimpl implements UserService, ApplicationContextAware{
 @Autowired
 private
 UserDao userDao = null;
 private ApplicationContext applicationContext = null; ／／实现生命周期方法，设置IoC容器自Override
 public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
 this.applicationContext = applicationContext; }
 @Transactional(isolation =Isolation.READ COMMITTED, propagation = Propagation. REQUIRED)
 public int insertUsers (List<User> userList) {
  int count =   0;
  ／／从工。c容器中取出代理对象
  UserService userService = applicationContext.getBean(UserService.class);
   for (User user userList) { ／／使用代理对象调用方法插入用户，此时会织入Spring数据库事务流程中
   count += userService.insertUser(user);
   }
   return count;
    }
   @Override @Transactional(isolation = Isolation.READ COMMITTED, propagation= Propagation.REQUIRES_NEW)
   public int insertUser (User user) {
   return userDao.insertUser(user);
   }
   }
 */
