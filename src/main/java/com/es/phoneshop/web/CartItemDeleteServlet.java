package com.es.phoneshop.web;

import com.es.phoneshop.logic.CartService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartItemDeleteServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cartService = CartService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pattern pattern = Pattern.compile("/\\d+");
        Matcher matcher = pattern.matcher(req.getRequestURI());
        matcher.find();
        String productCode = matcher.group().substring(matcher.start() + 1);
        Long id = Long.parseLong(productCode);
        cartService.deleteCartItem(req.getSession(), id);
        req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
    }
}
