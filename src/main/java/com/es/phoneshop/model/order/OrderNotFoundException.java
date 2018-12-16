package com.es.phoneshop.model.order;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super("Order with code " + message + " wasn't founded");
    }

    public OrderNotFoundException() {
        super("Order wasn't founded");
    }
}
