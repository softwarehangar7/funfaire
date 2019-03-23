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
	
	
}
