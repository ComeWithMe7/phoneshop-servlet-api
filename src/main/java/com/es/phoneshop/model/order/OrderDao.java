package com.es.phoneshop.model.order;

import com.es.phoneshop.model.cart.Cart;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.es.phoneshop.constants.ApplicationConstants.*;

public class OrderDao {
    private final Map<String, Order> orders;
    private static volatile OrderDao orderDaoInstance;

    public static OrderDao getInstance() {
        if (orderDaoInstance == null) {
            synchronized (OrderDao.class) {
                if (orderDaoInstance == null) {
                    orderDaoInstance = new OrderDao();
                }
            }
        }
        return orderDaoInstance;
    }

    private OrderDao() {
        orders = new ConcurrentHashMap<>();
    }

    public String save(HttpServletRequest req) {
        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        String phoneNumber = req.getParameter(PHONE_NUMBER);
        String address = req.getParameter(DELIVERY_ADDRESS);
        if (!checkOnValidData(firstName, lastName, phoneNumber, req)) {
            return null;
        } else {
            String id = UUID.randomUUID().toString();
            orders.put(id, new Order(firstName, lastName, phoneNumber, address, (Cart) req.getSession().getAttribute(CART)));
            return id;
        }
    }

    private boolean checkOnValidData(String firstName, String lastName, String phoneNumber, HttpServletRequest req) {
        if (!firstName.matches("[a-zA-Z]*")) {
            req.setAttribute("firstNameVald", "Wrong First Name");
            return false;
        }
        if (!lastName.matches("[a-zA-Z]*")) {
            req.setAttribute("lastNameVald", "Wrong Last Name");
            return false;
        }
        if (!phoneNumber.matches("\\+\\d{12}")) {
            req.setAttribute("phoneVald", "Wrong Phone Number");
            return false;
        }
        return true;
    }

    public Order getOrder(String orderID) {
        Order order = orders.get(orderID);
        if (order == null) {
            throw new OrderNotFoundException(orderID);
        } else {
            return order;
        }
    }

}
