package com.massimo.web.app.service;

import java.util.List;

import com.massimo.web.app.domain.dao.IProductDao;
import com.massimo.web.app.domain.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private IProductDao productDao;
	
	
	@Override
	public List<Product> findAll() {
		
		return (List<Product>) productDao.findAll();
	}

	@Override
	public void save(Product product) {
		productDao.save(product);
	}

	@Override
	public Product findById(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}

	@Override
	public Product findByName(String name) {
		return productDao.findByName(name);
	}

	@Override
	public List<Product> findByStateActive() {
		return productDao.findByState(true);
	}

	@Override
	public List<Product> findByNameContaining(String name) {
		return productDao.findByNameContaining(name);
	}

	@Override
	public void restartListProducts() {
		productDao.resetState();
	}


}
