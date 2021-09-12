package com.massimo.web.app.service.order;

import java.util.Date;
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
    public List<Order> listByDateReport(Date lowerDate, Date upperDate) {
        logger.info("lowerDate" + lowerDate + "///" + upperDate);
        if(lowerDate == null || upperDate == null) {
            return lowerDate == null ?
                    orderDao.findByDateAndStatus(upperDate, OrderStatus.PAGADO)
                    : orderDao.findByDateAndStatus(lowerDate, OrderStatus.PAGADO);
        }
        return orderDao.findByDateBetweenAndStatus(lowerDate, upperDate, OrderStatus.PAGADO);
    }

    @Override
    public Order save(Order order) {
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

    /*@Override
    public Order findByIdAndStatus(Long id, OrderStatus status) {
        return orderDao.findByIdAndStatus(id, status);
    }*/

    @Override
    public boolean existsById(Long id) {
        return orderDao.existsById(id);
    }

    @Override
    public void delete(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    public Order changeState(Long id, OrderStatus status) {
        Order order = orderDao.findById(id).orElse(null);
        if(order != null) {
            order.setStatus(status);
        }
        return orderDao.save(order);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        OrderDetail od = orderDetailDao.findByOrderAndProduct(
                orderDetail.getOrder().getIdOrder()
                ,orderDetail.getProduct().getIdProduct());
        if(od != null) {
            od.increaseQuantity(orderDetail.getQuantity());
            return orderDetailDao.save(od);
        }
        orderDetail.setProduct(productDao.findById(orderDetail.getProduct().getIdProduct()).orElse(null));
        return orderDetailDao.save(orderDetail);
    }

    @Override
    public boolean deleteOrderDetailById(Long id) {
        boolean isDeleted = orderDetailDao.existsById(id);
        orderDetailDao.deleteById(id);
        return isDeleted;
    }


}
