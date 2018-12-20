package com.es.phoneshop.web;

import com.es.phoneshop.logic.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class QuickOrderServlet extends HttpServlet {
    private CartService cartService = CartService.getInstance();

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/quickorder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> codes = Arrays.asList(req.getParameterValues("code"));
        List<String> quantities = Arrays.asList(req.getParameterValues("quantity"));
        cartService.addToCart(req.getSession(), codes, quantities);
    }

}
