package com.es.phoneshop.web;


import com.es.phoneshop.logic.CartService;
import com.es.phoneshop.model.cart.Cart;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import static com.es.phoneshop.constants.ApplicationConstants.CART;


public class MiniCartFilter implements Filter {

    private CartService cartService;

    @Override
    public void init(FilterConfig filterConfig) {
        cartService = CartService.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session =((HttpServletRequest) servletRequest).getSession();
        if (session.getAttribute(CART) == null) {
            session.setAttribute(CART, new Cart());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
