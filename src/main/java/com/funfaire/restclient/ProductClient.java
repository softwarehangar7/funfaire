package com.funfaire.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.funfaire.domain.product.ProductService;
import com.funfaire.restclient.wrapper.ProductsWrapper;

@Controller
public class ProductClient {

	private final static String BASE_URL = "https://www.faire-stage.com/";

	private HttpEntity<String> entity;

	@Autowired
	private RestTemplate rest;

	@Autowired
	private ProductService productService;

	public void setApiKey(final String apiKey) {
		final HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type","application/json");
		headers.set("X-FAIRE-ACCESS-TOKEN",apiKey);
		entity = new HttpEntity<>("paramters",headers);
	}

	public ProductsWrapper getAllProductsByBrand(final String brand_id) {
		final ProductsWrapper wrapper =  rest.exchange(BASE_URL + "api/v1/products?brand_id={brand_id}",
				HttpMethod.GET, entity, ProductsWrapper.class,brand_id)
				.getBody();

		//productService.saveAll(wrapper.getCollection());

		return wrapper;
	}
	
	
}
