package com.funfaire.domain.shipment;

import com.funfaire.domain.order.Order;

import lombok.Data;

@Data
public class Shipment {

	private String id;
	private Order order;
	
}
