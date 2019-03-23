package com.funfaire.domain.product;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funfaire.infra.deserializer.ProductDeserializer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@JsonDeserialize(using= ProductDeserializer.class)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk;
	@Column
	private String id;
	@Column private String brand_id;
	@Column private String short_description;
//	@Column private String description;
	@Column private Long wholesale_price_cents;
	@Column private Long retail_price_cents;
	@Column private Boolean active;
	@Column private String name;
	@Column private Long unit_multiplier;
	@OneToMany( mappedBy = "product" ) @Setter(AccessLevel.NONE)
	private final List<ProductOption> options = new LinkedList<ProductOption>();
	@Column private LocalDateTime created_at;
	@Column private LocalDateTime updated_at;
	
	public Product(final String id, final String brand_id, final String short_description, final String description, final Long wholesale_price_cents,
			final Long retail_price_cents, final Boolean active, final String name, final Long unit_multiplier, final LocalDateTime created_at,
			final LocalDateTime updated_at) {
		this.id = id;
		this.brand_id = brand_id;
		this.short_description = short_description;
		this.wholesale_price_cents = wholesale_price_cents;
		this.retail_price_cents = retail_price_cents;
		this.active = active;
		this.name = name;
		this.unit_multiplier = unit_multiplier;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public Product() {
		this(null, null, null, null, null, null, null, null, null, null, null);
	}
	
	
	public void addProductOptions(final ProductOption productOption) {
		options.add(productOption);
	}
	
	public List<ProductOption> getOptions(){
		return Collections.unmodifiableList(options);
	}


	
}
