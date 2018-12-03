// TODO not suitable package
package com.es.phoneshop.model.product.сart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    // TODO make this field final
    private List<CartItem> cartItemList;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public void save(CartItem cartItem) {
        cartItemList.add(cartItem);
    }

    public void delete(CartItem cartItem) {
        cartItemList.remove(cartItem);
    }

    // TODO return unmodifiable copy of the list
    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    // TODO remove this method
    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
