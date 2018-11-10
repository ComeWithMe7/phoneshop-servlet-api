package com.es.phoneshop.logic;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import java.util.List;

public class ProductLogic {
    private static ProductDao productDao = new ArrayListProductDao();

    public static List<Product> findProducts(){
        return productDao.findProducts();
    }
}
