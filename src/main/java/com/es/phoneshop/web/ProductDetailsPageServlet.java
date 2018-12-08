package com.es.phoneshop.web;

import com.es.phoneshop.logic.CartService;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.ViewedProducts;
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
            session.setAttribute(VIEWED_PRODUCTS, viewedProducts);
        }
        req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long productID = Long.parseLong(req.getParameter(PRODUCT_ID));
        try {
            int quantity = Integer.parseInt(req.getParameter(QUANTITY));
            try {
                cartService.addToCart(session, productID, quantity);
            } catch (RuntimeException ex) {
                req.setAttribute(QUANTITY_ANSWER, "Not enough stock");
                req.setAttribute("product", productDao.getProduct(productID));
                req.setAttribute(QUANTITY, req.getParameter(QUANTITY));
                req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
            }
            String path = req.getRequestURI() + "?quantityAnswer=" + ADWANTAGED_ANSWER + "&quantity=" + quantity;
            resp.sendRedirect(path);
        } catch (NumberFormatException ex) {
            req.setAttribute(QUANTITY_ANSWER, "Not a number");
            req.setAttribute("product", productDao.getProduct(productID));
            req.setAttribute(QUANTITY, req.getParameter(QUANTITY));
            req.getRequestDispatcher("/WEB-INF/pages/productDetails.jsp").forward(req, resp);
        }
    }
}
