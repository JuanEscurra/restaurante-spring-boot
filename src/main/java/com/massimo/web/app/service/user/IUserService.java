package com.massimo.web.app.service.user;

import java.util.List;

import com.massimo.web.app.domain.entity.User;

public interface IUserService {
	
	List<User> findAll();
	void save(User user);
	User findOne(Long id);
	void delete(Long id);
	List<User> findByLastName(String lastName);
}
