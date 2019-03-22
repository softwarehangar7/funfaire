package com.funfaire.domain.product;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funfaire.infra.deserializer.ProductOptionDeserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonDeserialize(using= ProductOptionDeserializer.class)
public class ProductOption {

	@Id
	private String id;
	private String product_id;
	private Boolean active;
	private String name;
	private String sku;
	private Long available_quantity;
	private LocalDateTime backordered_until;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
}
