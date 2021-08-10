package com.massimo.web.app.models.service;

import java.util.List;

import com.massimo.web.app.models.entity.User;

public interface IUserService {
	
	public List<User> findAll();
	public void save(User user);
	public User findOne(Long id);
	public void delete(Long id);
	public List<User> findByLastName(String lastName);
}
