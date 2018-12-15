package com.es.phoneshop.logic;

import com.es.phoneshop.model.сart.Cart;
import com.es.phoneshop.model.сart.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import static com.es.phoneshop.constants.ApplicationConstants.*;

public class CartService {

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

    private CartService() {   }

    public boolean addToCart(HttpSession session, Long productID, int quantity) {
        Cart cart = (Cart)session.getAttribute(CART);
        if (cart == null) {
            cart = new Cart();
            if (saveCartItem(cart, new CartItem(productID, quantity))) {
                session.setAttribute(CART, cart);
                return true;
            }
        } else {
            return saveCartItem(cart, new CartItem(productID, quantity));
        }
        return false;
    }

    private boolean saveCartItem(Cart cart, CartItem cartItem) {
        Optional<CartItem> optionalCartItem = cart.getCartItemList().stream()
                .filter(x -> x.getProductID().equals(cartItem.getProductID()))
                .findAny();
        if (optionalCartItem.isPresent()) {
            CartItem existingCartItem = optionalCartItem.get();
            cart.delete(existingCartItem.getProductID());
            int quantity = existingCartItem.getQuantity() + cartItem.getQuantity();
            if (cartItem.getQuantity() <=0 || quantity <= 0 || quantity > cartItem.getProduct().getStock()) {
                cart.save(existingCartItem);
                cart.countTotal();
                return false;
            }
            cart.save(new CartItem(existingCartItem.getProductID(), quantity));
            cart.countTotal();
            return true;
        } else {
            if (cartItem.getQuantity() <=0 || cartItem.getQuantity() > cartItem.getProduct().getStock()) {
                return false;
            }
            cart.save(cartItem);
            cart.countTotal();
            return true;
        }
    }

    public boolean updateCart(HttpServletRequest request, List<String> quantitiesString, List<String> idsString) {
        Cart cart = (Cart)request.getSession().getAttribute(CART);
        List<Long> ids = new ArrayList<>();
        idsString.stream()
                .mapToLong(Long::parseLong)
                .forEach(ids::add);
        Map<Long, String> answers = new HashMap<>();
        Map<Long, String> inputQuantities = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            if (cart.getById(ids.get(i)) != null) {
                inputQuantities.put(ids.get(i), quantitiesString.get(i));
                try {
                    int quantity = Integer.parseInt(quantitiesString.get(i));
                    if (quantity > 0 && quantity < cart.getById(ids.get(i)).getProduct().getStock()) {
                        cart.getById(ids.get(i)).setQuantity(quantity);
                        answers.put(ids.get(i), "");
                    } else {
                        answers.put(ids.get(i), INVALID_QUANTITY);
                    }
                } catch (NumberFormatException ex) {
                    answers.put(ids.get(i), NOT_A_NUMBER);
                }
            }
        }
        cart.countTotal();
        request.setAttribute(QUANTITY_MAP, inputQuantities);
        request.setAttribute(ANSWERS, answers);
        if (answers.containsValue(NOT_A_NUMBER) || answers.containsValue(INVALID_QUANTITY)) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteCartItem(HttpSession session, Long id) {
        Cart cart = (Cart)session.getAttribute(CART);
        cart.delete(id);
        cart.countTotal();
    }

}
