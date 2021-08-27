package com.massimo.web.app.service.user;

import java.util.List;

import com.massimo.web.app.domain.dao.IUserDao;
import com.massimo.web.app.domain.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private PasswordEncoder bCryptPassword;
	
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
		user.setPassword(bCryptPassword.encode(user.getPassword()));
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
