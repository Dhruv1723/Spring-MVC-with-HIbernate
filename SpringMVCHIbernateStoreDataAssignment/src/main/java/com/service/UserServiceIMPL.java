package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.User;
import com.dao.UserDAO;

@Service
public class UserServiceIMPL implements UserService{

	@Autowired
	private UserDAO uDao;
	
	@Transactional
	public void addUser(User u) {
		
		uDao.addUser(u);
	}

	
	@Transactional
	public int login(String username, String password) {
	
		if(uDao.login(username, password)) {	//This uDao.login() returns boolean value so If True---then service will return 1
			return 1;
		}else {
			return 0;
		}
	}

}
