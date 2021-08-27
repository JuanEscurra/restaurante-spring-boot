package com.massimo.web.app.service.order;

import java.util.List;

import com.massimo.web.app.domain.entity.Order;

public interface IOrderService {
    
    List<Order> findAll();
	void save(Order order);
	Order findOne(Long id);
	void delete(Long id);
}
