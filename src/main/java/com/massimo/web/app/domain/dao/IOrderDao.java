package com.massimo.web.app.domain.dao;

import java.util.List;

import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderDetail;
import com.massimo.web.app.domain.entity.OrderStatus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends CrudRepository<Order,Long> {

    
    List<Order> findByStatus(OrderStatus status);
    
    List<Order> findByTableNumberAndStatus(Integer tableNumber, OrderStatus status);

}
