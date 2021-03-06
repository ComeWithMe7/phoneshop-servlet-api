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

import static com.es.phoneshop.constants.ApplicationConstants.QUANTITY_ANSWER;
import static com.es.phoneshop.constants.ApplicationConstants.SUCSESSFUL_UPDATE;

public class CartItemDeleteServlet extends HttpServlet {

    private CartService cartService;
    private Pattern pattern;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cartService = CartService.getInstance();
        pattern = Pattern.compile("/\\d+/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Matcher matcher = pattern.matcher(req.getRequestURI());
        matcher.find();
        String productCode = matcher.group().substring(1, matcher.group().length() - 1);
        Long id = Long.parseLong(productCode);
        cartService.deleteCartItem(req.getSession(), id);
        String path = req.getContextPath() + "/cart?" + QUANTITY_ANSWER + "=" + SUCSESSFUL_UPDATE;
        resp.sendRedirect(path);
    }
}
