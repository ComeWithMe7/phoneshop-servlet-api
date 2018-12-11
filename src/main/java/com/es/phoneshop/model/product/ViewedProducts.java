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
                // TODO redundant check
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

    // TODO it's better return Iterable<Product>. Explain why.
    public Queue<Product> getProductList() {
        return productList;
    }
}
