package com.funfaire.restclient.wrapper;

import java.util.Collection;
import java.util.Collections;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.funfaire.domain.product.Product;
import com.funfaire.infra.deserializer.ProductsWrapperDeserializer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonDeserialize(using= ProductsWrapperDeserializer.class)
public class ProductsWrapper {

    private final Collection<Product> collection;

    public Collection<Product> getCollection() {
        return Collections.unmodifiableCollection(collection);
    }
	
}
