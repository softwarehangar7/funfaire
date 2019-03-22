package com.funfaire.domain.product;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
	@OneToMany private final List<ProductOption> options = new LinkedList<ProductOption>();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSSX")
	@Column private LocalDateTime created_at;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSSX")
	@Column private LocalDateTime updated_at;
	
}
