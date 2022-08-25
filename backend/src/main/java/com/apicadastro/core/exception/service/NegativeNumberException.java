package com.apicadastro.core.exception.service;

public class NegativeNumberException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegativeNumberException(String msg) {
        super(msg);
    }
}