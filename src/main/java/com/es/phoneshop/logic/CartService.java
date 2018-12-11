package com.es.phoneshop.logic;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.сart.Cart;
import com.es.phoneshop.model.сart.CartItem;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static com.es.phoneshop.constants.ApplicationConstants.CART;
import static com.es.phoneshop.constants.ApplicationConstants.INVALID_QUANTITY;
import static com.es.phoneshop.constants.ApplicationConstants.NOT_A_NUMBER;

public class CartService {

    // TODO never used
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
            // TODO unnecessary
            session.setAttribute(CART, cart);
        }
    }

    // TODO some logic can be moved to Cart
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
                // TODO don't throw an exception, store error in ...Result bean
                throw new RuntimeException();
            }
            cart.save(new CartItem(existingCartItem.getProductID(), quantity));
        } else {
            if (cartItem.getQuantity() > cartItem.getProduct().getStock()) {
                // TODO don't throw an exception, store error in ...Result bean
                throw new RuntimeException();
            }
            cart.save(cartItem);
        }
    }

    // TODO cart update result must not be stored in a session
    public boolean updateCart(HttpSession session, List<String> quantitiesString) {
        Cart cart = (Cart)session.getAttribute(CART);
        List<CartItem> cartItems = cart.getCartItemList();
        boolean update = true;
        for (int i = 0; i < quantitiesString.size(); i++) {
            cartItems.get(i).setInputQuantity(quantitiesString.get(i));
            try {
                Integer quantity = Integer.parseInt(quantitiesString.get(i));
                if (quantity >= 0 && quantity < cartItems.get(i).getProduct().getStock()) {
                    cartItems.get(i).setQuantity(quantity);
                    cartItems.get(i).setAnswer("");
                } else {
                    cartItems.get(i).setAnswer(INVALID_QUANTITY);
                    update = false;
                }
            } catch (NumberFormatException ex) {
                cartItems.get(i).setAnswer(NOT_A_NUMBER);
                update = false;
            }
        }
        // TODO not necessary
        session.setAttribute(CART, cart);
        return update;
    }

    public void deleteCartItem(HttpSession session, Long id) {
        Cart cart = (Cart)session.getAttribute(CART);
        // TODO add delete by product id method to Cart class
        List<CartItem> cartItems = cart.getCartItemList();
        cartItems.removeIf(x -> x.getProductID().equals(id));
        // TODO why do you set the same list cart returned?
        cart.setCartItemList(cartItems);
        // TODO not necessary
        session.setAttribute(CART, cart);
    }

}
