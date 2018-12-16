package com.es.phoneshop.web;

import com.es.phoneshop.model.order.OrderDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.es.phoneshop.constants.ApplicationConstants.ORDER;

public class OrderOverviewPageServlet extends HttpServlet {
    private OrderDao orderDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderDao = OrderDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String orderID = uri.substring(uri.lastIndexOf("/") + 1);
        req.setAttribute(ORDER, orderDao.getOrder(orderID));
        req.getRequestDispatcher("/WEB-INF/pages/orderOverview.jsp").forward(req, resp);
    }
}
