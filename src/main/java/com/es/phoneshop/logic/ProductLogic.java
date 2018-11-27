package com.es.phoneshop.logic;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.product.RequestEnums;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductLogic {
    private ProductDao productDao;
    private static volatile ProductLogic productLogicInstance;

    public static ProductLogic getInstance() {
        if (productLogicInstance == null) {
            synchronized (ProductLogic.class) {
                if (productLogicInstance == null) {
                    productLogicInstance = new ProductLogic();
                }
            }
        }
        return productLogicInstance;
    }

    private ProductLogic() {
        productDao = ArrayListProductDao.getInstance();
    }

    public List<Product> findProducts(HttpServletRequest request){
        List<Product> productList = productDao.findProducts();
        String searchLine = request.getParameter(RequestEnums.SEARCH_LINE.getValue());
        if (searchLine != null && !searchLine.isEmpty()) {
            request.setAttribute(RequestEnums.SEARCH_LINE_ATTRIBUTE.getValue(), searchLine);
            productList = searchLine(searchLine, productList);
        }
        String sortingParameter = request.getParameter(RequestEnums.SORTING_PARAMETER.getValue());
        return sortingByParam(sortingParameter, productList);
    }

    private List<Product> searchLine(String searchLine, List<Product> productList) {
        String[] searchWords = searchLine.split("\\s+");
        productList = productList.stream().filter(x -> {
            for (String str : searchWords) {
                if (x.getDescription().toUpperCase().contains(str.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }).sorted((x, y) -> {
            int containCounterX = 0;
            int containCounterY = 0;
            for (String str : searchWords) {
                if (x.getDescription().toUpperCase().contains(str.toUpperCase())) {
                    containCounterX++;
                }
                if (y.getDescription().toUpperCase().contains(str.toUpperCase())) {
                    containCounterY++;
                }
            }
            return containCounterY - containCounterX;
        }).collect(Collectors.toList());

        return productList;
    }

    private List<Product> sortingByParam(String sortingParameter, List<Product> productList) {
        if (sortingParameter != null) {
            switch (RequestEnums.getEnum(sortingParameter)) {
                case UP_DESCRIPTION:
                    return productList.stream().sorted(Comparator.comparing(Product::getDescription)).collect(Collectors.toList());
                case DOWN_DESCRIPTION:
                    return productList.stream().sorted(Comparator.comparing(Product::getDescription).reversed()).collect(Collectors.toList());
                case UP_PRICE:
                    return productList.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
                case DOWN_PRICE:
                    return productList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
                default:
                    return productList;
            }
        } else {
            return productList;
        }
    }

}
