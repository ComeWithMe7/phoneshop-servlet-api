package com.es.phoneshop.model.сart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<CartItem> cartItemList;
    private BigDecimal total = BigDecimal.ZERO;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public void save(CartItem cartItem) {
        cartItemList.add(cartItem);
    }

    public List<CartItem> getCartItemList() {
        return Collections.unmodifiableList(cartItemList);
    }

    public CartItem getById(Long id) {
        return cartItemList.stream()
                .filter(x -> x.getProductID().equals(id))
                .findAny().orElse(null);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void countTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (CartItem cartItem : cartItemList) {
            sum = sum.add(cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        }
        total = sum;
    }

    public void delete(Long id) {
        cartItemList.removeIf(x -> x.getProductID().equals(id));
    }
}
