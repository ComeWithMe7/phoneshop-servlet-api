package com.es.phoneshop.command;

import com.es.phoneshop.logic.ProductLogic;
import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest httpServletRequest) {
        ProductLogic productLogic = ProductLogic.getInstance();
        List<Product> products = productLogic.findProducts(httpServletRequest);
        httpServletRequest.setAttribute("products", products);
        return "/WEB-INF/pages/productList.jsp";
    }
}
