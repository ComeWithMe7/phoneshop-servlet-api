package com.es.phoneshop.web;

import com.es.phoneshop.logic.CartService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.es.phoneshop.constants.ApplicationConstants.*;

public class CartPageServlet extends HttpServlet {

    private CartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cartService = CartService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(QUANTITY_ANSWER,req.getParameter(QUANTITY_ANSWER));
        req.getRequestDispatcher("/WEB-INF/pages/cartPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> quantities = Arrays.asList(req.getParameterValues("quantity"));
        if (!cartService.updateCart(req.getSession(), quantities)) {
            req.setAttribute(QUANTITY_ANSWER, UNSUCSESSFUL_UPDATE);
            req.getRequestDispatcher("/WEB-INF/pages/cartPage.jsp").forward(req, resp);
        } else {
            String path = req.getRequestURI() + "?" + QUANTITY_ANSWER + "=" + SUCSESSFUL_UPDATE;
            resp.sendRedirect(path);
        }
    }
}
