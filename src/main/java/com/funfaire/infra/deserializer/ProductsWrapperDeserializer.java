package com.funfaire.infra.deserializer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.funfaire.domain.product.Product;
import com.funfaire.restclient.wrapper.ProductsWrapper;

import lombok.AllArgsConstructor;

public class ProductsWrapperDeserializer extends AbstractDeserializer<ProductsWrapper>{

	@Override
	public ProductsWrapper deserialize(final JsonNode jsonNode) {
		final ObjectMapper mapper = new ObjectMapper();
		final ArrayNode arrayNode = (ArrayNode) getArrayNodeValue(jsonNode, Field.PRODUCTS);
		List<Product> products = null;
		try {
			products = mapper.readValue(arrayNode.toString(), new TypeReference<List<Product>>() {});
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ProductsWrapper(products);
	}

	
    @AllArgsConstructor
    private enum Field {

        PRODUCTS("products");

        private final String label;

        @Override
        public String toString() {
            return this.label;
        }
    }
	
	
}
