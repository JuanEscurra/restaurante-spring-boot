package com.massimo.web.app.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	
	@NotEmpty
	@Column(unique = true)
	private String name;
	
	private Double price;
	
	private String type;

	private Boolean state;
	
	private Integer stock;

	public void reduceStock(Integer quantity) {
		this.stock -= quantity;
	}

	public void increaseStock(Integer quantity) {
		this.stock +=  quantity;
	}
}
