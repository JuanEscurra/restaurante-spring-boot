package com.massimo.web.app.service;

import java.util.List;

import com.massimo.web.app.domain.entity.User;

public interface IUserService {
	
	public List<User> findAll();
	public void save(User user);
	public User findOne(Long id);
	public void delete(Long id);
	public List<User> findByLastName(String lastName);
}
