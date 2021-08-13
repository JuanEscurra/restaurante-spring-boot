package com.massimo.web.app.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Byte tableNumber;
    
    private Double totalPrice;
    
    private OrderStatus status;

}
