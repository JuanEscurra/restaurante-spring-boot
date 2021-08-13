package com.massimo.web.app.service;

import java.util.List;

import com.massimo.web.app.domain.entity.Product;

public interface IProductService {
	
	public List<Product> findAll();
	public void save(Product product);
	public Product findById(Long id);
	public void deleteById(Long id);
	public Product findByName(String name);
	public List<Product> findByNameContaining(String name);
	public List<Product> findByStateActive(); 
	
	public void restartListProducts();
}
