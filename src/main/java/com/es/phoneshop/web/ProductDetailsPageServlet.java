package com.es.phoneshop.web;

import com.es.phoneshop.logic.CartService;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ViewedProducts;
import com.es.phoneshop.model.product.сart.Cart;
import com.es.phoneshop.model.product.сart.CartItem;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.es.phoneshop.constants.ApplicationConstants.QUANTITY;
import static com.es.phoneshop.constants.ApplicationConstants.QUANTITY_ANSWER;
import static com.es.phoneshop.constants.ApplicationConstants.VIEWED_PRODUCTS;
import static com.es.phoneshop.constants.ApplicationConstants.CART;
import static com.es.phoneshop.constants.ApplicationConstants.PRODUCT_ID;
import static com.es.phoneshop.constants.ApplicationConstants.ADWANTAGED_ANSWER;



public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDao productDao;

    @Override
    public void init(ServletConfig config) {
        productDao = ArrayListProductDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String productCode = uri.substring(uri.lastIndexOf("/") + 1);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(productCode);
        matcher.find();
        productCode = matcher.group();

        Product product = productDao.getProduct(Long.parseLong(productCode));
        req.setAttribute("product", product);
        req.setAttribute(QUANTITY_ANSWER,req.getParameter(QUANTITY_ANSWER));
        req.setAttribute(QUANTITY, req.getParameter(QUANTITY));

        HttpSession session = req.getSession();
        ViewedProducts viewedProducts = (ViewedProducts) session.getAttribute(VIEWED_PRODUCTS);
        if (viewedProducts == null) {
            viewedProducts = new ViewedProducts();
            viewedProducts.push(product);
            session.setAttribute(VIEWED_PRODUCTS, viewedProducts);
        } else {
            viewedProducts.push(product);
            session.removeAttribute(VIEWED_PRODUCTS);
            session.setAttribute(VIEWED_PRODUCTS, viewedProducts);
        }
        req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart)session.getAttribute(CART);
        Product product = productDao.getProduct(Long.parseLong(req.getParameter(PRODUCT_ID)));
        try {
            int quantity = Integer.parseInt(req.getParameter(QUANTITY));
            // TODO move as much cart logic as possible to cart service
            if (cart == null) {
                cart = new Cart();
                cart.save(new CartItem(product, quantity));
                session.setAttribute(CART, cart);
                String path = req.getContextPath() + "/products/" + req.getParameter(PRODUCT_ID) + "?quantityAnswer=" + ADWANTAGED_ANSWER + "&quantity=" + quantity;
                resp.sendRedirect(path);
            } else {
                // TODO service classes generally mast have only an instance across entire application
                // TODO cartService should be a member variable
                CartService cartService = new CartService(cart);
                CartItem cartItem = new CartItem(product, quantity);
                try {
                    cartService.addToCart(cartItem);
                    // TODO don't use exceptions for flow controlling
                } catch (RuntimeException ex) {
                    String path = req.getContextPath() + "/products/" + req.getParameter(PRODUCT_ID) + "?quantityAnswer=" + "Not enough stock" + "&quantity=" + quantity;
                    // TODO in case of low stock there is should be forward instead of redirect
                    resp.sendRedirect(path);
                }
                // TODO redundant method call
                session.removeAttribute(CART);
                // TODO it's better delegate to cartService rather to servlet
                session.setAttribute(CART, cartService.getCart());

                // TODO use String.format
                String path = req.getContextPath() + "/products/" + req.getParameter(PRODUCT_ID) + "?quantityAnswer=" + ADWANTAGED_ANSWER + "&quantity=" + quantity;
                resp.sendRedirect(path);

            }
        } catch (NumberFormatException ex) {
            req.setAttribute(QUANTITY_ANSWER, "Not a number");
            req.setAttribute("product", product);
            req.setAttribute(QUANTITY, req.getParameter(QUANTITY));
            req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
        }

    }
}
