package com.es.phoneshop.model.product;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class MostViewedProducts {
    private static final int MAX = 3;
    private final Map<Long, AtomicInteger> mostViewedProducts;
    private static volatile MostViewedProducts mostViewedProductsInstance;

    public static MostViewedProducts getInstance() {
        if (mostViewedProductsInstance == null) {
            synchronized (MostViewedProducts.class) {
                if (mostViewedProductsInstance == null) {
                    mostViewedProductsInstance = new MostViewedProducts();
                }
            }
        }
        return mostViewedProductsInstance;
    }

    private MostViewedProducts() { mostViewedProducts = new ConcurrentHashMap<>(); }

    public void addView(Long id) {
        mostViewedProducts.put(id, new AtomicInteger(mostViewedProducts.get(id).incrementAndGet()));
    }

    public void add(Long id) {
        mostViewedProducts.put(id, new AtomicInteger(0));
    }

    public void delete(Long id) {
        mostViewedProducts.remove(id);
    }

    public List<Long> getProducts() {
        return mostViewedProducts.entrySet().stream()
                .sorted((x, y) -> Integer.valueOf(x.getKey().intValue()).compareTo(Integer.valueOf(y.getKey().intValue())))
                .skip(mostViewedProducts.size() - MAX)
                .map(x -> x.getKey())
                .collect(Collectors.toList());
    }
}
