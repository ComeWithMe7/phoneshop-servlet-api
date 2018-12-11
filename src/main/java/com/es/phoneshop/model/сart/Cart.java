package com.es.phoneshop.model.сart;

import java.util.ArrayList;
import java.util.List;

// TODO implement add, update, delete methods. Don't allow to modify cartItemList to other classes directly.
public class Cart {
    private List<CartItem> cartItemList;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public void save(CartItem cartItem) {
        cartItemList.add(cartItem);
    }

    // TODO return unmodifiable copy of items
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
