package com.es.phoneshop.model.product;

import java.util.LinkedList;
import java.util.Queue;

public class ViewedProducts {
    private Queue<Product> productList;
    private final int maxSize = 3;

    public ViewedProducts() {
        this.productList = new LinkedList<Product>();
    }

    private void pop() {
        if (productList != null && productList.size() > 0) {
            productList.remove();
        }
    }
    public void push(Product product) {
        if (productList != null) {
            if (productList.size() < maxSize) {
                productList.offer(product);
            } else {
                pop();
                productList.offer(product);
            }
        }
    }
}
