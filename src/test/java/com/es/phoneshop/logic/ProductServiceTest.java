package com.es.phoneshop.logic;

import com.es.phoneshop.constants.ApplicationConstants;
import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private List<Product> productList;
    private Currency usd = Currency.getInstance("USD");

    @Mock
    private ProductDao productDao;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private ProductService productService;

    @Before
    public void setup() {
        productDao = ArrayListProductDao.getInstance();
        productDao.save(new Product(1L, "sgs", "Samsung Galaxy S", new BigDecimal(100), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));
        productDao.save(new Product(2L, "sgs2", "Samsung Galaxy S II", new BigDecimal(200), usd, 13, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg"));
        productList = productDao.findProducts();
    }

    @Test
    public void findProductsWithNullStringSearchLineTest() {
        when(request.getParameter(ApplicationConstants.SEARCH_LINE)).thenReturn(null);
        assertThat(productService.findProducts(request).size(), is(productList.size()));
    }
}