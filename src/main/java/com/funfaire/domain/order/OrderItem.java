package com.funfaire.domain.order;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funfaire.domain.product.Product;
import com.funfaire.domain.product.ProductOption;
import com.funfaire.infra.deserializer.OrderItemDeserializer;

import lombok.Data;

@Data
@JsonDeserialize(using= OrderItemDeserializer.class)
public class OrderItem {

	private String id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Order order;
	private Product product;
	private ProductOption productOption;
	private Long quantity;
	private String sku;
	private Boolean includesTester; 
	private Long price_cents;
	
	public OrderItem(final String id, final LocalDateTime createdAt, final LocalDateTime updatedAt, final Long quantity, final String sku,
			final Boolean includesTester, final Long price_cents) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.quantity = quantity;
		this.sku = sku;
		this.includesTester = includesTester;
		this.price_cents = price_cents;
	}
	
	public OrderItem() {
		this(null, null, null, null, null, null, null);
	}
}
