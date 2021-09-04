package com.massimo.web.app.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date(System.currentTimeMillis());
    
    @Column(name = "table_number", nullable = false)
    private Integer tableNumber;
    
    @Column(name = "total_price")
    private Double totalPrice;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> details = new ArrayList<>();


    public void setIdOrder(Long idOrder) {
    	this.idOrder = idOrder;
    }
    
    public Long getIdOrder() {
    	return this.idOrder;
    }

    public Double calculateTotalPrice() {
        return details.stream().map((detail)-> detail.calculateAmount())
                .reduce((amount, accumulator)-> amount + accumulator).orElse(null);
    }
}
