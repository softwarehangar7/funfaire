package com.funfaire.infra.deserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funfaire.domain.product.Product;

import lombok.AllArgsConstructor;

@Component
public class ProductDeserializer extends AbstractDeserializer<Product>{
	
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSX");
	
	@Override
	public Product deserialize(final JsonNode jsonNode) {
		final ObjectMapper mapper = new ObjectMapper();
		final String id = getFieldTextValue(jsonNode, Field.ID);
		final String brand_id = getFieldTextValue(jsonNode, Field.BRAND_ID);
		final String short_desc = getFieldTextValue(jsonNode, Field.SHORT_DESCRIPTION);
		final String description = getFieldTextValue(jsonNode, Field.DESCRIPTION);
		final Long wholesale_price = getFieldLongValue(jsonNode, Field.WHOLESALE_PRICE);
		final Long retailPrice = getFieldLongValue(jsonNode, Field.RETAIL_PRICE);
		final Boolean active = getFieldBooleanValue(jsonNode, Field.ACTIVE);
		final String name = getFieldTextValue(jsonNode, Field.NAME);
		final Long unitMultiplier = getFieldLongValue(jsonNode, Field.UNIT_MULTIPLIER);
		final LocalDateTime createdAt = getFieldLocalDateTime(jsonNode, Field.CREATED_AT, DATE_FORMATTER);
		final LocalDateTime updatedAt = getFieldLocalDateTime(jsonNode, Field.UPDATED_AT, DATE_FORMATTER);
		
		final Product product = new Product(id, brand_id, short_desc, description, wholesale_price, retailPrice, active, name, unitMultiplier, createdAt, updatedAt);

		/*
		 * final JsonNode productOptionsNode = getArrayNodeValue(jsonNode,
		 * Field.OPTIONS); List<ProductOption> options = null; try { options =
		 * mapper.readValue(productOptionsNode.toString(), new
		 * TypeReference<List<ProductOption>>() {}); options.forEach(option ->
		 * product.addProductOptions(option)); } catch (final IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		
		return product;
	}

    @AllArgsConstructor
    private enum Field {

        ID("id"),
        BRAND_ID("brand_id"),
        SHORT_DESCRIPTION("short_description"),
        DESCRIPTION("description"),
        WHOLESALE_PRICE("wholesale_price_cents"),
        RETAIL_PRICE("retail_price_cents"),
        ACTIVE("active"),
        NAME("name"),
        UNIT_MULTIPLIER("unit_multiplier"),
        OPTIONS("options"),
        CREATED_AT("created_at"),
        UPDATED_AT("updated_at");

        private final String label;

        @Override
        public String toString() {
            return this.label;
        }
    }
	
	
}
