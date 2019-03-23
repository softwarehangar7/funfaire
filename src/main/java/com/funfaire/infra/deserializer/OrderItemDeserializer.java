package com.funfaire.infra.deserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.funfaire.domain.order.OrderItem;

import lombok.AllArgsConstructor;

public class OrderItemDeserializer extends AbstractDeserializer<OrderItem>{

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSX");
	
	@Override
	public OrderItem deserialize(final JsonNode jsonNode) {
		final String id = getFieldTextValue(jsonNode, Field.ID);
		final LocalDateTime createdAt = getFieldLocalDateTime(jsonNode, Field.CREATED_AT, DATE_FORMATTER);
		final LocalDateTime updatedAt = getFieldLocalDateTime(jsonNode, Field.CREATED_AT, DATE_FORMATTER);;
		//final Order order;
		//final Product product;
		//final ProductOption productOption;
		final Long quantity = getFieldLongValue(jsonNode, Field.QUANTITY);
		final String sku = getFieldTextValue(jsonNode, Field.SKU);
		final Boolean includesTester = getFieldBooleanValue(jsonNode, Field.TESTER); 
		final Long price_cents = getFieldLongValue(jsonNode, Field.PRICE_CENTS);

		final OrderItem orderItem = new OrderItem(id, createdAt, updatedAt, quantity, sku, includesTester, price_cents);
		
		return orderItem;
	}

    @AllArgsConstructor
    private enum Field {

        ID("id"),
        CREATED_AT("createdAt"),
        UPDATED_AT("updatedAt"),
        ORDER("order"),
        PRODUCT("product"),
        PRODUCT_OPTION("productOption"),
        QUANTITY("quantity"),
        SKU("sku"),
        TESTER("includesTester"),
        PRICE_CENTS("price_cents");

        private final String label;

        @Override
        public String toString() {
            return this.label;
        }
    }
	
}
