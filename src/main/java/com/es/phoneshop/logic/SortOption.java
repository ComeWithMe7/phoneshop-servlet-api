package com.es.phoneshop.logic;

import com.es.phoneshop.model.product.Product;

import java.util.Comparator;

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
        switch (sortParam) {
            case "upDescription":
                return DESCRIPTION_ASC;
            case "downDescription":
                return DESCRIPTION_DESC;
            case "upPrice":
                return PRICE_ASC;
            case "downPrice":
                return PRICE_DESC;
        }
        throw new IllegalArgumentException("No enum constant for sort param: " + sortParam);
    }
}