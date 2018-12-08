package com.es.phoneshop.model.сart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItemList;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public void save(CartItem cartItem) {
        cartItemList.add(cartItem);
    }

    public List<CartItem> getCartItemList() {
        return Collections.unmodifiableList(cartItemList);
    }

    public void delete(CartItem cartItem) {
        cartItemList.remove(cartItem);
    }
}
