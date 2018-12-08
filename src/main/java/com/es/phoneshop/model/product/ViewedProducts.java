package com.es.phoneshop.model.product;

import java.util.ArrayDeque;
import java.util.Queue;

public class ViewedProducts {
    private final Queue<Product> productList;
    private static final int maxSize = 3;

    public ViewedProducts() {
        this.productList = new ArrayDeque<>(maxSize);
    }

    public void push(Product product) {
            if (productList.size() < maxSize) {
                if (productList.contains(product)) {
                    productList.remove(product);
                }
                productList.offer(product);
            } else {
                if (productList.contains(product)) {
                    productList.remove(product);
                } else {
                    productList.remove();
                }
                productList.offer(product);
            }
    }

    public Queue<Product> getProductList() {
        return productList;
    }
}
