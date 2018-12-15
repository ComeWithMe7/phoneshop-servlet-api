package com.es.phoneshop.logic;

import com.es.phoneshop.model.product.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import static java.util.Comparator.comparing;

public enum SortOption {

    DESCRIPTION_ASC(comparing(Product::getDescription)),
    DESCRIPTION_DESC(comparing(Product::getDescription).reversed()),
    PRICE_ASC(comparing(Product::getPrice)),
    PRICE_DESC(comparing(Product::getPrice).reversed());

    private final Comparator<Product> comparator;

    SortOption(Comparator<Product> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Product> getComparator() {
        return comparator;
    }

    public static SortOption from(String sortParam) {
        Optional<SortOption> sortOption = Arrays.stream(SortOption.values()).filter(x -> sortParam.equals(x)).findAny();
        if (!sortOption.isPresent()) {
            throw new IllegalArgumentException("No enum constant for sort param: " + sortParam);
        } else {
            return sortOption.get();
        }
    }
}