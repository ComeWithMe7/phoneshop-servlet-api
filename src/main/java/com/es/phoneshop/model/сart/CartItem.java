package com.es.phoneshop.model.сart;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import java.util.Objects;

public class CartItem {
    private final Long productID;
    private int quantity;
    private String answer;
    private String inputQuantity;
    private static final ProductDao products = ArrayListProductDao.getInstance();

    public CartItem(Long productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
        inputQuantity = Integer.valueOf(quantity).toString();
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        inputQuantity = Integer.valueOf(quantity).toString();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getInputQuantity() {
        return inputQuantity;
    }

    public void setInputQuantity(String inputQuantity) {
        this.inputQuantity = inputQuantity;
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
