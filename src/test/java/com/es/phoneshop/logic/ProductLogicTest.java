package com.es.phoneshop.logic;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductDao;
import com.es.phoneshop.model.product.RequestEnums;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductLogicTest {

    private List<Product> productList;
    @Mock
    private ProductDao productDao;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private ProductLogic productLogic;

    @Before
    public void setup() {
        productDao = ArrayListProductDao.getInstance();
        productList = productDao.findProducts();
        productLogic = ProductLogic.getInstance();
    }

    @Test
    public void findProductsWithNullStringSearchLineTest() {
        when(request.getParameter(RequestEnums.SEARCH_LINE.getValue())).thenReturn(null);
        assertThat(productLogic.findProducts(request).size(), is(productList.size()));
    }
}