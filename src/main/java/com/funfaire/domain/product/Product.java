package com.funfaire.domain.product;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funfaire.infra.deserializer.ProductDeserializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonDeserialize(using= ProductDeserializer.class)
public class Product {

	@Id
	private String id;
	@Column private String brand_id;
	@Column private String short_description;
	@Column private String description;
	@Column private Long wholesale_price_cents;
	@Column private Long retail_price_cents;
	@Column private Boolean active;
	@Column private String name;
	@Column private Long unit_multiplier;
	@OneToMany @Setter(AccessLevel.NONE)
	private final List<ProductOption> options = new LinkedList<ProductOption>();
	@Column private LocalDateTime created_at;
	@Column private LocalDateTime updated_at;
	
	public void addProductOptions(final ProductOption productOption) {
		options.add(productOption);
	}
	
	public List<ProductOption> getOptions(){
		return Collections.unmodifiableList(options);
	}
	
}
