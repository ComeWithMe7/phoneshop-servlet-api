package com.es.phoneshop.model.product;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private final List<Product> products;
    private static volatile ArrayListProductDao arrayListProductDaoInstance;
    private final MostViewedProducts mostViewedProducts = MostViewedProducts.getInstance();

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
        mostViewedProducts.add(product.getId());
    }

    @Override
    public void delete(Long id) {
        mostViewedProducts.delete(id);
        products.removeIf(x -> x.getId().equals(id));
    }

    public List<Product> listOfProducts() {
        return Collections.unmodifiableList(products);
    }

}
