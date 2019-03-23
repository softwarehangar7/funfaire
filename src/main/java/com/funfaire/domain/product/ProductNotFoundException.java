package com.funfaire.domain.product;

import com.funfaire.infra.exception.ApplicationException;

public class ProductNotFoundException extends ApplicationException {

    public ProductNotFoundException(final String message) {
        super(message);
    }

}