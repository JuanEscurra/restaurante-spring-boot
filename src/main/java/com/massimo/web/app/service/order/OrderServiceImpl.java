package com.massimo.web.app.service.order;

import java.util.List;

import com.massimo.web.app.domain.dao.IOrderDao;
import com.massimo.web.app.domain.dao.IOrderDetailDao;
import com.massimo.web.app.domain.dao.IProductDao;
import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderDetail;
import com.massimo.web.app.domain.entity.OrderStatus;

import com.massimo.web.app.domain.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IOrderDetailDao orderDetailDao;

    @Autowired
    private IProductDao productDao;

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderDao.findAll();
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return orderDao.findByStatus(status);
    }

    @Override
    public Order save(Order order) {
        logger.info("qweasd" + orderDao.findByTableNumberAndStatus(order.getTableNumber(), OrderStatus.POR_ATENDER));
        if(orderDao.findByTableNumberAndStatus(order.getTableNumber(), OrderStatus.POR_ATENDER).size() == 0) {
            return orderDao.save(order);
        } else {
            return null;
        }
    }

    @Override
    public Order findOne(Long id) {
        return orderDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    public Order finish(Long id) {
        Order order = orderDao.findById(id).orElse(null);
        if(order != null) {
            order.setTotalPrice(order.calculateTotalPrice());
            order.setStatus(OrderStatus.ATENDIDO);
        }
        return orderDao.save(order);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        Product product = productDao.findById(orderDetail.getProduct().getIdProduct()).orElse(null);
        OrderDetail od = orderDetailDao.findByOrderAndProduct(
                orderDetail.getOrder().getIdOrder()
                ,orderDetail.getProduct().getIdProduct());
        logger.info("orderdetail: " + od);
        product.reduceStock(orderDetail.getQuantity());
        orderDetail.setProduct(product);

        if(od != null) {
            orderDetail.setIdOrderDetail(od.getIdOrderDetail());
            orderDetail.increaseQuantity(od.getQuantity());
        }
        return orderDetailDao.save(orderDetail);

    }

    @Override
    public boolean deleteOrderDetailById(Long id) {
        boolean isDeleted = orderDetailDao.existsById(id);
        OrderDetail detail = orderDetailDao.findById(id).orElse(null);
        detail.getProduct().increaseStock(detail.getQuantity());
        orderDetailDao.deleteById(id);

        return isDeleted;
    }
}
