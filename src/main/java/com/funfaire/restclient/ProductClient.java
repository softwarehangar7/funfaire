package com.funfaire.restclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.funfaire.restclient.wrapper.ProductsWrapper;

@Controller
public class ProductClient {

	private final static String BASE_URL = "https://www.faire-stage.com/";
	
	private final RestTemplate rest;
	
	public ProductClient(final RestTemplate restTemplate) {
		this.rest = restTemplate;
	}
	
	
	public ProductsWrapper getAllProducts(final String apiKey) {
		 final HttpHeaders headers = new HttpHeaders();
		 headers.set("Content-Type","application/json");
		 headers.set("X-FAIRE-ACCESS-TOKEN",apiKey);
		 final HttpEntity<String> entity = new HttpEntity<>("paramters",headers);
		
		return rest.exchange(BASE_URL + "api/v1/products",
	            HttpMethod.GET, entity, ProductsWrapper.class)
	        .getBody();
	}
	
	
}
