package com.massimo.web.app.service.order;

import java.util.Date;
import java.util.List;

import com.massimo.web.app.domain.dao.IOrderDao;
import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderDao.findAll();
    }

    @Override
    public void save(Order order) {
        if(order.getStatus() == null) {
        	order.setStatus(OrderStatus.POR_ATENDER);
        } else if(order.getStatus().equals(OrderStatus.POR_ATENDER)) {
        	order.setStatus(OrderStatus.ATENDIDO);
        }
        orderDao.save(order);
    }

    @Override
    public Order findOne(Long id) {
        return orderDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        orderDao.deleteById(id);
    }
    
}
