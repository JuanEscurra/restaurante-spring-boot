package com.massimo.web.app.domain.dao;

import com.massimo.web.app.domain.entity.Order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends CrudRepository<Order,Long> {

    
    
}
