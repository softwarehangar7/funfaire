package com.funfaire.domain.product;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funfaire.infra.deserializer.ProductOptionDeserializer;

import lombok.Data;

@Data
@Entity
@JsonDeserialize(using= ProductOptionDeserializer.class)
public class ProductOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk;
	
	@Column
	private String id;
	@Column private Boolean active;
	@Column private String name;
	@Column private String sku;
	@Column private Long available_quantity;
	@Column private LocalDateTime backordered_until;
	@Column private LocalDateTime created_at;
	@Column private LocalDateTime updated_at;
	
	@ManyToOne( fetch = FetchType.LAZY)
	private Product product;

	public ProductOption(final String id, final Boolean active, final String name, final String sku, final Long available_quantity,
			final LocalDateTime backordered_until, final LocalDateTime created_at, final LocalDateTime updated_at) {
		this.id = id;
		this.active = active;
		this.name = name;
		this.sku = sku;
		this.available_quantity = available_quantity;
		this.backordered_until = backordered_until;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public ProductOption() {
		this(null, null, null, null, null, null, null, null);
	}
}
