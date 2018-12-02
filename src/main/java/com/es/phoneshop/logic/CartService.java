package com.es.phoneshop.logic;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.сart.Cart;
import com.es.phoneshop.model.product.сart.CartItem;

import java.util.Optional;

public class CartService {

    private Cart cart;

    public CartService(Cart cart) {
        this.cart = cart;
    }

    public void addToCart(CartItem cartItem) {
        Optional<CartItem> optionalCartItem = cart.getCartItemList().stream()
                .filter(x -> x.getProduct().equals(cartItem.getProduct()))
                .findFirst();
        if (optionalCartItem.isPresent()) {
            CartItem existingCartItem = optionalCartItem.get();
            cart.delete(existingCartItem);
            int quantity = existingCartItem.getQuantity() + cartItem.getQuantity();
            if (quantity > ArrayListProductDao.getInstance().getProduct(cartItem.getProduct().getId()).getStock()) {
                throw new RuntimeException();
            }
            cart.save(new CartItem(existingCartItem.getProduct(), quantity));
        } else {
            if (cartItem.getQuantity() > ArrayListProductDao.getInstance().getProduct(cartItem.getProduct().getId()).getStock()) {
                throw new RuntimeException();
            }
            cart.save(cartItem);
        }
    }

    public Cart getCart() {
        return cart;
    }
}
