package com.massimo.web.app.service.order;

import java.util.List;

import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderDetail;
import com.massimo.web.app.domain.entity.OrderStatus;

public interface IOrderService {
    
    List<Order> findAll();
	List<Order> findByStatus(OrderStatus status);
	Order save(Order order);
	Order findOne(Long id);
	void delete(Long id);
	Order finish(Long id);
	OrderDetail save(OrderDetail detail);
	boolean deleteOrderDetailById(Long id);
}
