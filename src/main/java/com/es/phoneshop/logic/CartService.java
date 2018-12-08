package com.es.phoneshop.logic;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.сart.Cart;
import com.es.phoneshop.model.сart.CartItem;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.es.phoneshop.constants.ApplicationConstants.CART;

public class CartService {

    private final ProductDao productDao;
    private static volatile CartService cartServiceInstance;

    public static CartService getInstance() {
        if (cartServiceInstance == null) {
            synchronized (CartService.class) {
                if (cartServiceInstance == null) {
                    cartServiceInstance = new CartService();
                }
            }
        }
        return cartServiceInstance;
    }

    private CartService() {
        productDao = ArrayListProductDao.getInstance();
    }

    public void addToCart(HttpSession session, Long productID, int quantity) {
        Cart cart = (Cart)session.getAttribute(CART);
        if (cart == null) {
            cart = new Cart();
            saveCartItem(cart, new CartItem(productID, quantity));
            session.setAttribute(CART, cart);
        } else {
            saveCartItem(cart, new CartItem(productID, quantity));
            session.setAttribute(CART, cart);
        }
    }

    private void saveCartItem(Cart cart, CartItem cartItem) {
        Optional<CartItem> optionalCartItem = cart.getCartItemList().stream()
                .filter(x -> x.getProductID().equals(cartItem.getProductID()))
                .findFirst();
        if (optionalCartItem.isPresent()) {
            CartItem existingCartItem = optionalCartItem.get();
            cart.delete(existingCartItem);
            int quantity = existingCartItem.getQuantity() + cartItem.getQuantity();
            if (quantity > cartItem.getProduct().getStock()) {
                cart.save(existingCartItem);
                throw new RuntimeException();
            }
            cart.save(new CartItem(existingCartItem.getProductID(), quantity));
        } else {
            if (cartItem.getQuantity() > cartItem.getProduct().getStock()) {
                throw new RuntimeException();
            }
            cart.save(cartItem);
        }
    }

}
