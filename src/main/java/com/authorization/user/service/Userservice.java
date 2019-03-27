package com.authorization.user.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.authorization.user.dao.userdao;
import com.authorization.user.entity.user;

@Service("UserService")
public class Userservice {
	
	@Autowired
	userdao userDao;
	
	public user saveUser(user userData) {

		return (user)userDao.saveOrUpdate(userData);
	}
	
	
	public String deleteUser(user userData) {

		return userDao.deleteRecord(userData);
	}
	

	public List<Object> Userlist() {

		return userDao.getUserList();
	}
	
	public List<Object> UserlistbyParam(user userData) {
		
		DetachedCriteria dc =  DetachedCriteria.forClass(user.class);
		dc.add(Restrictions.eq("username", userData.getUsername()));

		return userDao.getUserListByParam(dc);
	}
	
}
