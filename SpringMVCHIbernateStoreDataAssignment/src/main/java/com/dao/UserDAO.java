package com.dao;

import com.bean.User;

public interface UserDAO {

	public void addUser(User u);
	public boolean login(String username, String password);
}
