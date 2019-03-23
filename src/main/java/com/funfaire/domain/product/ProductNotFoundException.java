package com.funfaire.domain.product;

import com.funfaire.infra.exception.ApplicationException;

public class ProductNotFoundException extends ApplicationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(final String message) {
        super(message);
    }

}