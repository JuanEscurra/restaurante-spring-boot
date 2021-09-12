package com.massimo.web.app.domain.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Getter
@Setter
@ToString
@Table(name = "order_detail")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order_detail")
	private Long idOrderDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	@ToString.Exclude
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Order order;
	
	@OneToOne()
	@JoinColumn(name="product_id")
	private Product product;

	@JsonProperty("quantity")
	private Integer quantity;


	public Double calculateAmount() {
		return product.getPrice() * quantity;
	}

	public void increaseQuantity(Integer quantity) {
		this.product.reduceStock(quantity);
		this.quantity += quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderDetail that = (OrderDetail) o;
		return idOrderDetail.equals(that.idOrderDetail) && product.equals(that.product) && quantity.equals(that.quantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idOrderDetail, product, quantity);
	}

	@PreRemove
	public void beforeRemove() {
		this.product.increaseStock(quantity);
	}

	@PrePersist
	public void beforePersist() {
		this.product.reduceStock(quantity);
	}
}
