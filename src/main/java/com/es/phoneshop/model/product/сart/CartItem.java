package com.es.phoneshop.model.product.сart;

import com.es.phoneshop.model.product.Product;

import java.util.Objects;

public class CartItem {
    // TODO make this field final
    // TODO store product code or id in the session
    private Product product;
    // TODO make this field final
    private int quantity;

    // TODO remove unused constructor
    public CartItem() { }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return getQuantity() == cartItem.getQuantity() &&
                Objects.equals(getProduct(), cartItem.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getQuantity());
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
