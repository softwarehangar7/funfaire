package com.funfaire.domain.order;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funfaire.domain.address.Address;
import com.funfaire.domain.shipment.Shipment;
import com.funfaire.infra.deserializer.OrderDeserializer;

import lombok.Data;

@Data
@JsonDeserialize(using= OrderDeserializer.class)
public class Order {

	private String id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private OrderState state;
	private final List<OrderItem> items = new LinkedList<OrderItem>();
	private final List<Shipment> shipments = new LinkedList<Shipment>();
	private Address address;
	
	
	public Order(final String id, final LocalDateTime createdAt, final LocalDateTime updatedAt, final OrderState state, final Address address) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.state = state;
		this.address = address;
	}
	
	public Order() {
		this(null, null, null, null, null);
	}
	
	public void addOrderItem(final OrderItem orderItem) {
		items.add(orderItem);
	}
	
	public List<OrderItem> getItems(){
		return Collections.unmodifiableList(items);
	}
	
	public void addShipment(final Shipment shipment) {
		shipments.add(shipment);
	}
	
	public List<Shipment> getShipments(){
		return Collections.unmodifiableList(shipments);
	}
	
	public BigInteger getTotal() {
		return items.stream()
				.reduce(BigInteger.ZERO, (t, u) -> t.add(BigInteger.valueOf(u.getPrice_cents())), BigInteger::add);
	}
}
