package com.es.phoneshop.model.сart;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import java.util.Objects;

public class CartItem {
    private final Long productID;
    private final int quantity;
    private static final ProductDao products = ArrayListProductDao.getInstance();

    public CartItem(Long productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return products.getProduct(productID);
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getProductID() {
        return productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return getQuantity() == cartItem.getQuantity() &&
                Objects.equals(getProductID(), cartItem.getProductID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getQuantity());
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
