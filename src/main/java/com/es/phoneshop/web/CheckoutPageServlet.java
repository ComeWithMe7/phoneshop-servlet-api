package com.es.phoneshop.web;

import com.es.phoneshop.model.order.OrderDao;
import com.es.phoneshop.model.сart.Cart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.constants.ApplicationConstants.*;

public class CheckoutPageServlet extends HttpServlet {
    private OrderDao orderDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderDao = OrderDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/checkoutPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = orderDao.save(req);
        if (id == null) {
            req.setAttribute(FIRST_NAME, req.getParameter(FIRST_NAME));
            req.setAttribute(LAST_NAME, req.getParameter(LAST_NAME));
            req.setAttribute(PHONE_NUMBER, req.getParameter(PHONE_NUMBER));
            req.setAttribute(DELIVERY_ADDRESS, req.getParameter(DELIVERY_ADDRESS));
            req.getRequestDispatcher("WEB-INF/pages/checkoutPage.jsp").forward(req, resp);
        } else {
            req.getSession().removeAttribute(CART);
            String path = req.getContextPath() + "/order/overview/" + id;
            resp.sendRedirect(path);
        }
    }
}
