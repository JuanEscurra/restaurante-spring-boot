package com.massimo.web.app.domain.dao;

import com.massimo.web.app.domain.entity.OrderDetail;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrderDetailDao extends CrudRepository<OrderDetail,Long> {

    @Query("select od from OrderDetail od where od.order.idOrder = ?1 and od.product.idProduct = ?2")
    OrderDetail findByOrderAndProduct(Long IdOrder, Long idProduct);
}
