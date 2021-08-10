package com.massimo.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.massimo.web.app.models.entity.Role;

public interface IRoleDao extends CrudRepository<Role, Integer>{

}
