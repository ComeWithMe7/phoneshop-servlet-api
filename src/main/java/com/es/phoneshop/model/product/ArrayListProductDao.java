package com.es.phoneshop.model.product;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private final List<Product> products;
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
        products = new CopyOnWriteArrayList<>();
    }

    @Override
    public Product getProduct(Long id) {
        return products.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> findProducts() {
        return products.stream()
                .filter(x -> x.getPrice() != null)
                .filter(x -> x.getStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product)  {
        products.add(product);
    }

    @Override
    public void delete(Long id) { products.removeIf(x -> x.getId().equals(id)); }

}
