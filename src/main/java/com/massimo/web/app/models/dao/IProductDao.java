package com.massimo.web.app.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.massimo.web.app.models.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long> {
	@Query("select p from Product p where p.name = ?1")
	Product findByName(String name);
	
	List<Product> findByNameContaining(String lastName);
	
	@Query("select p from Product p where p.state = ?1")
	List<Product> findByState(Boolean state);
	
	@Transactional
	@Modifying
	@Query("update Product p set p.state=false, p.stock=0 where p.state=true ")
	void resetState();
}
