package com.es.phoneshop.model.product;

import javax.servlet.http.HttpSession;
import java.util.ArrayDeque;
import java.util.Queue;

import static com.es.phoneshop.constants.ApplicationConstants.VIEWED_PRODUCTS;

public class ViewedProducts {
    private final Queue<Product> productList;
    private static final int maxSize = 3;

    public ViewedProducts() {
        this.productList = new ArrayDeque<>(maxSize);
    }

    public void push(Product product) {
            if (productList.size() < maxSize) {
                productList.remove(product);
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

    public Iterable<Product> getProductList() {
        return productList;
    }

    public static void setupCiewedProducts(HttpSession session, Product product) {
        ViewedProducts viewedProducts = (ViewedProducts) session.getAttribute(VIEWED_PRODUCTS);
        if (viewedProducts == null) {
            viewedProducts = new ViewedProducts();
            viewedProducts.push(product);
            session.setAttribute(VIEWED_PRODUCTS, viewedProducts);
        } else {
            viewedProducts.push(product);
            session.setAttribute(VIEWED_PRODUCTS, viewedProducts);
        }
    }
}
