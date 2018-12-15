package com.es.phoneshop.web;

import com.es.phoneshop.logic.CartService;
import com.es.phoneshop.model.product.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static com.es.phoneshop.constants.ApplicationConstants.QUANTITY;
import static com.es.phoneshop.constants.ApplicationConstants.QUANTITY_ANSWER;
import static com.es.phoneshop.constants.ApplicationConstants.PRODUCT_ID;
import static com.es.phoneshop.constants.ApplicationConstants.ADWANTAGED_ANSWER;



public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDao productDao;
    private CartService cartService;

    @Override
    public void init(ServletConfig config) {
        productDao = ArrayListProductDao.getInstance();
        cartService = CartService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String productCode = uri.substring(uri.lastIndexOf("/") + 1);
        if (!productCode.matches("\\d+$")){
            throw new ProductNotFoundException(productCode);
        }
        Product product = productDao.getProduct(Long.parseLong(productCode));
        req.setAttribute("product", product);
        req.setAttribute(QUANTITY_ANSWER,req.getParameter(QUANTITY_ANSWER));
        req.setAttribute(QUANTITY, req.getParameter(QUANTITY));
        ViewedProducts.setupCiewedProducts(req.getSession(), product);
        req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long productID = Long.parseLong(req.getParameter(PRODUCT_ID));
        try {
            int quantity = Integer.parseInt(req.getParameter(QUANTITY));
            if (cartService.addToCart(session, productID, quantity)) {
                String path = req.getRequestURI() + "?quantityAnswer=" + ADWANTAGED_ANSWER + "&quantity=" + quantity;
                resp.sendRedirect(path);
            } else {
                req.setAttribute(QUANTITY_ANSWER, "Not enough stock");
                req.setAttribute("product", productDao.getProduct(productID));
                req.setAttribute(QUANTITY, req.getParameter(QUANTITY));
                req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
            }
        } catch (NumberFormatException ex) {
            req.setAttribute(QUANTITY_ANSWER, "Not a number");
            req.setAttribute("product", productDao.getProduct(productID));
            req.setAttribute(QUANTITY, req.getParameter(QUANTITY));
            req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
        }
    }
}
