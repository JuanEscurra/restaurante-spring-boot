package com.massimo.web.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.massimo.web.app.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long>{
	
	List<User> findByLastNameContaining(String lastName);
	
}
