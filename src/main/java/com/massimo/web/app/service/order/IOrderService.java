package com.massimo.web.app.service.order;

import java.util.Date;
import java.util.List;

import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderDetail;
import com.massimo.web.app.domain.entity.OrderStatus;

public interface IOrderService {
    
    List<Order> findAll();
	List<Order> findByStatus(OrderStatus status);
	List<Order> listByDateReport(Date lowerDate, Date upperDate);
	Order save(Order order);
	Order findOne(Long id);
	//Order findByIdAndStatus(Long id, OrderStatus status);
	boolean existsById(Long id);
	void delete(Long id);

	Order changeState(Long id, OrderStatus status);
	OrderDetail save(OrderDetail detail);
	boolean deleteOrderDetailById(Long id);


}
