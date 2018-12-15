package com.es.phoneshop.model.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super("Product with code " + message + " wasn't founded");
    }

    public ProductNotFoundException() {
        super("Product wasn't founded");
    }
}
