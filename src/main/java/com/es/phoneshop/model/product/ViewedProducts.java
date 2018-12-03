package com.es.phoneshop.model.product;

import java.util.LinkedList;
import java.util.Queue;

public class ViewedProducts {
    // TODO make this field final
    private Queue<Product> productList;
    // TODO class constants must be static
    private final int maxSize = 3;

    public ViewedProducts() {
        // TODO ArrayDeque is faster than LinkedList when used as a queue
        // TODO use diamond operator
        // TODO use capacity
        this.productList = new LinkedList<Product>();
    }

    // TODO remove this method, logic flow can be simplified
    private void pop() {
        // TODO what's the reason of checking productList on null?
        if (productList != null && productList.size() > 0) {
            productList.remove();
        }
    }
    public void push(Product product) {
        // TODO what's the reason of checking productList on null?
        if (productList != null) {
            // TODO logic flow can be simplified
            if (productList.size() < maxSize) {
                productList.offer(product);
            } else {
                pop();
                productList.offer(product);
            }
        }
    }
}
