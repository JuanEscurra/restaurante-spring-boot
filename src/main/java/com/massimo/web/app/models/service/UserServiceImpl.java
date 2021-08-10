package com.massimo.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.massimo.web.app.models.dao.IUserDao;
import com.massimo.web.app.models.entity.User;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	@Transactional
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userDao.deleteById(id);
	}
	
	public List<User> findByLastName(String lastName) {
			return userDao.findByLastNameContaining(lastName);
	}
	
}
