package com.massimo.web.app.service.product;

import java.util.List;

import com.massimo.web.app.domain.entity.Product;

public interface IProductService {
	
	List<Product> findAll();
	void save(Product product);
	Product findById(Long id);
	void deleteById(Long id);
	Product findByName(String name);
	List<Product> findByNameContaining(String name);
	List<Product> findByStateActive(); 
	
	void restartListProducts();
}
