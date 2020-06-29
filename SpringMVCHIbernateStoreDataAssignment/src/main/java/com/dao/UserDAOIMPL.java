package com.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.User;


@Repository
public class UserDAOIMPL implements UserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addUser(User u) {
		
		sessionFactory.getCurrentSession().save(u);
	}

	public boolean login(String username, String password) {
		Query q = sessionFactory.getCurrentSession()
				.createQuery("from User u where username=:uname and password=:pass");
		q.setParameter("uname", username);
		q.setParameter("pass", password);
		
		List<User> ulist = q.getResultList();
		Iterator<User> itr=ulist.iterator();
		if(itr.hasNext()) {
			return true;
		}
		else {
			return false;
		}
	}

}
