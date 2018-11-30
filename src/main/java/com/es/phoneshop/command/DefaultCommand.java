package com.es.phoneshop.command;

import com.es.phoneshop.logic.ProductService;
import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest httpServletRequest) {
        ProductService productService = ProductService.getInstance();
        List<Product> products = productService.findProducts(httpServletRequest);
        httpServletRequest.setAttribute("products", products);
        return "/WEB-INF/pages/productList.jsp";
    }
}
