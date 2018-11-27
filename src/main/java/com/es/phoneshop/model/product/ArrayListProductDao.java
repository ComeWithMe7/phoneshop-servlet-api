package com.es.phoneshop.model.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private List<Product> products;
    private Currency usd = Currency.getInstance("USD");
    private static volatile ArrayListProductDao arrayListProductDaoInstance;

    public static ArrayListProductDao getInstance() {
        if (arrayListProductDaoInstance == null) {
            synchronized (ArrayListProductDao.class) {
                if (arrayListProductDaoInstance == null) {
                    arrayListProductDaoInstance = new ArrayListProductDao();
                }
            }
        }
        return arrayListProductDaoInstance;
    }

    private ArrayListProductDao() {
        products = Collections.synchronizedList(new ArrayList<>(13));
    }

    @Override
    public Product getProduct(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new RuntimeException("Product with code " + id + " not found");
    }

    @Override
    public List<Product> findProducts() {
        return products.stream().filter(x -> x.getPrice() != null)
                .filter(x -> x.getStock() > 0).collect(Collectors.toList());
    }

    @Override
    public void save(Product product)  {
        products.add(product);
    }

    @Override
    public void delete(Long id) {
        boolean correctID;
        synchronized (this) {
            correctID = products.removeIf(x -> x.getId().equals(id));
        }
        if (!correctID) {
            throw new RuntimeException("Wrong");
        }
    }
}
