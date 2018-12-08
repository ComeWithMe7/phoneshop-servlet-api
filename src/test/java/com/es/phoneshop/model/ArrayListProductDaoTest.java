package com.es.phoneshop.model;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayListProductDaoTest {
    private ArrayListProductDao productDao;

    @Before
    public void setup() {
        productDao = ArrayListProductDao.getInstance();
    }

    @Test
    public void testFindProductsIsNotEmpty() {
        assertThat(productDao.findProducts().isEmpty(), is(false));
    }

    @Test
    public void testFindProducts() {
        assertThat(productDao.findProducts().size(), is(12));
    }

    @Test(expected = RuntimeException.class)
    public void testGetProductWithInvalidID() {
        productDao.getProduct(100L);
    }

    @Test
    public void testGetProductWithValidID() {
        assertThat(productDao.getProduct(5L).equals(productDao.getProduct(5L)), is(true));
    }

    @Test
    public void testSaveProduct() {
        int sizeBeforeSaving = productDao.findProducts().size();
        productDao.save(new Product(10L, "palmp", "Palm Pixi", new BigDecimal(170), Currency.getInstance("USD"), 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg"));
        assertThat(productDao.findProducts().size(), is(sizeBeforeSaving + 1));
    }

    @Test
    public void testDeliteWithValidID() {
        int sizeBeforeDelition = productDao.findProducts().size();
        productDao.delete(5L);
        assertThat(productDao.findProducts().size(), is(sizeBeforeDelition - 1));
    }

    @Test(expected = RuntimeException.class)
    public void testDeliteWithInvalidID() {
        productDao.delete(100L);
    }
}


