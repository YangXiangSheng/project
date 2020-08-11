package com.springboot.demorest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.demorest.dao.UserDao;
import com.springboot.demorest.pojo.User;
import com.springboot.demorest.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao = null;
	
	@Override
	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	@Override
	public List<User> findUsers(String userName, String note, int start, int limit) {
		return userDao.findUsers(userName, note, start, limit);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int updateUserName(Long id, String userName) {
		return userDao.updateUserName(id, userName);
	}

	@Override
	public int deleteUser(Long id) {
		return userDao.deleteUser(id);
	}

	@Override
	public User insertUser(User user) {
		return userDao.insertUser(user) >0 ? user  : null;
	}
	
}
