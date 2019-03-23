package com.funfaire.infra.exception;

public abstract class ApplicationException extends RuntimeException {

    public ApplicationException(final String message) {
        super(message);
    }

    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
