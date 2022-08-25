package com.apicadastro.core.exception.service;

public class UnprocessableEntity extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnprocessableEntity(String msg) {
        super(msg);
    }
}