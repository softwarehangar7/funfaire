package com.funfaire.infra.deserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.funfaire.domain.order.OrderItem;
import com.funfaire.domain.product.Product;
import com.funfaire.domain.product.ProductOption;
import com.funfaire.domain.product.ProductOptionRepository;
import com.funfaire.domain.product.ProductService;

import lombok.AllArgsConstructor;

@Component
public class OrderItemDeserializer extends AbstractDeserializer<OrderItem>{

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss.SSSX");
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductOptionRepository productOptionRepository;
	
	
	@Override
	public OrderItem deserialize(final JsonNode jsonNode) {
		final String id = getFieldTextValue(jsonNode, Field.ID);
		final LocalDateTime createdAt = getFieldLocalDateTime(jsonNode, Field.CREATED_AT, DATE_FORMATTER);
		final LocalDateTime updatedAt = getFieldLocalDateTime(jsonNode, Field.CREATED_AT, DATE_FORMATTER);;
		//final Order order;
		final String product_id = getFieldTextValue(jsonNode, Field.PRODUCT);
		final Product product = productService.getProductById(product_id);
		final String productOption_id = getFieldTextValue(jsonNode, Field.PRODUCT_OPTION);
		final ProductOption productOption = productOptionRepository.getOne(productOption_id);
		final Long quantity = getFieldLongValue(jsonNode, Field.QUANTITY);
		final String sku = getFieldTextValue(jsonNode, Field.SKU);
		final Boolean includesTester = getFieldBooleanValue(jsonNode, Field.TESTER); 
		final Long price_cents = getFieldLongValue(jsonNode, Field.PRICE_CENTS);

		final OrderItem orderItem = new OrderItem(id, createdAt, updatedAt, quantity, sku, includesTester, price_cents);
		orderItem.setProduct(product);
		orderItem.setProductOption(productOption);
		
		return orderItem;
	}

    @AllArgsConstructor
    private enum Field {

        ID("id"),
        CREATED_AT("createdAt"),
        UPDATED_AT("updatedAt"),
        ORDER("order_id"),
        PRODUCT("product_id"),
        PRODUCT_OPTION("productOption_id"),
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
