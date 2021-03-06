package com.massimo.web.app.domain.dao;

import java.util.List;

import com.massimo.web.app.domain.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	
	List<User> findByLastNameContaining(String lastName);
	
}
