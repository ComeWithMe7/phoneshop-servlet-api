package com.es.phoneshop.model.сart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItemList;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public void save(CartItem cartItem) {
        cartItemList.add(cartItem);
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void delete(CartItem cartItem) {
        cartItemList.remove(cartItem);
    }
}
