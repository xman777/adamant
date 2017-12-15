package com.adamant.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adamant.dao.UserDao;
import com.adamant.entity.UserEntity;
import com.adamant.service.IUserService;


@Service("userService")  
public class UserServiceImpl implements IUserService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
    private UserDao userDao; 
	
	@Override
	public UserEntity getUserById(String userId) {
		// TODO Auto-generated method stub
		logger.warn("=======================userService getUserById()==================");
		return this.userDao.selectByPrimaryKey(userId);
	}

}
