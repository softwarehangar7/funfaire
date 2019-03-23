package com.funfaire.infra.deserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.funfaire.domain.product.ProductOption;

import lombok.AllArgsConstructor;

public class ProductOptionDeserializer extends AbstractDeserializer<ProductOption>{

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSX");
	
	@Override
	public ProductOption deserialize(final JsonNode jsonNode) {
		final String id = getFieldTextValue(jsonNode, Field.ID);
		final Boolean active = getFieldBooleanValue(jsonNode, Field.ACTIVE);
		final String name = getFieldTextValue(jsonNode, Field.NAME);
		final String sku = getFieldTextValue(jsonNode, Field.SKU);
		final Long available_quantity = getFieldLongValue(jsonNode, Field.AVAILABLE_QUANTITY);
		final LocalDateTime backordered_until = getFieldLocalDateTime(jsonNode, Field.BACKORDERED_UNTIL, DATE_FORMATTER);
		final LocalDateTime created_at = getFieldLocalDateTime(jsonNode, Field.CREATED_AT, DATE_FORMATTER);
		final LocalDateTime updated_at = getFieldLocalDateTime(jsonNode, Field.UPDATED_AT, DATE_FORMATTER);
		
		return new ProductOption(id, active, name, sku, available_quantity, backordered_until, created_at, updated_at);
	}

    @AllArgsConstructor
    private enum Field {

        ID("id"),
        PRODUCT_ID("product_id"),
        ACTIVE("active"),
        NAME("name"),
        SKU("sku"),
        AVAILABLE_QUANTITY("available_quantity"),
        BACKORDERED_UNTIL("backordered_until"),
        CREATED_AT("created_at"),
        UPDATED_AT("updated_at");

        private final String label;

        @Override
        public String toString() {
            return this.label;
        }
    }
}
