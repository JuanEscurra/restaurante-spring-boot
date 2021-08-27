package com.massimo.web.app.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(name = "date_created", nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT-05:00")
    private Date date = new Date(System.currentTimeMillis());
    
    @Column(name = "table_number", nullable = false)
    private Integer tableNumber;
    
    @Column(name = "total_price")
    private Double totalPrice;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetail> details;

    public void setIdOrder(Long idOrder) {
    	this.idOrder = idOrder;
    }
    
    public Long getIdOrder() {
    	return this.idOrder;
    }
}
