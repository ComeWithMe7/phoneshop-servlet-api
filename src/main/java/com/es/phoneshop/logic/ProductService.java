package com.es.phoneshop.logic;

import com.es.phoneshop.constants.ApplicationConstants;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.es.phoneshop.logic.SortOption.*;

public class ProductService {
    private final ProductDao productDao;
    private static volatile ProductService productServiceInstance;

    public static ProductService getInstance() {
        if (productServiceInstance == null) {
            synchronized (ProductService.class) {
                if (productServiceInstance == null) {
                    productServiceInstance = new ProductService();
                }
            }
        }
        return productServiceInstance;
    }

    private ProductService() {
        productDao = ArrayListProductDao.getInstance();
    }

    public List<Product> findProducts(HttpServletRequest request){
        List<Product> productList = productDao.findProducts();
        String searchLine = request.getParameter(ApplicationConstants.SEARCH_LINE);
        if (searchLine != null && !searchLine.isEmpty()) {
            request.setAttribute(ApplicationConstants.SEARCH_LINE_ATTRIBUTE, searchLine);
            productList = searchLine(searchLine, productList);
        }
        String sortingParameter = request.getParameter(ApplicationConstants.SORTING_PARAMETER);
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
            switch (SortOption.from(sortingParameter)) {
                case DESCRIPTION_ASC:
                    productList.sort(DESCRIPTION_ASC.getComparator());
                    return productList;
                case DESCRIPTION_DESC:
                    productList.sort(DESCRIPTION_DESC.getComparator());
                    return productList;
                case PRICE_ASC:
                    productList.sort(PRICE_ASC.getComparator());
                    return productList;
                case PRICE_DESC:
                    productList.sort(PRICE_DESC.getComparator());
                    return productList;
                default:
                    return productList;
            }
        } else {
            return productList;
        }
    }

}
