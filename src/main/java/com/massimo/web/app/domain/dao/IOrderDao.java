package com.massimo.web.app.domain.dao;

import java.util.Date;
import java.util.List;

import com.massimo.web.app.domain.entity.Order;
import com.massimo.web.app.domain.entity.OrderDetail;
import com.massimo.web.app.domain.entity.OrderStatus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends CrudRepository<Order,Long> {

    @Query("select o from Order o where o.status = ?1 order by o.date desc")
    List<Order> findByStatus(OrderStatus status);

    List<Order> findByTableNumberAndStatus(Integer tableNumber, OrderStatus status);

    @Query("select o from Order o where o.status = ?3 and o.date between ?1 and ?2 order by o.date desc")
    List<Order> findByDateBetweenAndStatus(Date lowerDate, Date uppDate, OrderStatus status);

    @Query("select o from Order o where date(o.date) = date(?1) and o.status = ?2 order by o.date desc")
    List<Order> findByDateAndStatus(Date date, OrderStatus status);
    //Order findByIdAndStatus(Long id, OrderStatus status);
}
