package com.service;

import com.bean.User;

public interface UserService {

	public void addUser(User u);
	public int login(String username, String password);
}
