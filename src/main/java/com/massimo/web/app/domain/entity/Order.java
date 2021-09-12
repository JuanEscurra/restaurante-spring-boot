package com.massimo.web.app.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
    private Date date;
    
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

    @PrePersist
    public void beforePersist() {
        this.date = new Date();
        this.status = OrderStatus.POR_ATENDER;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.totalPrice = calculateTotalPrice();
    }

    public Double calculateTotalPrice() {
        return details.stream().map((detail)-> detail.calculateAmount())
                .reduce((amount, accumulator)-> amount + accumulator).orElse(null);
    }
}
