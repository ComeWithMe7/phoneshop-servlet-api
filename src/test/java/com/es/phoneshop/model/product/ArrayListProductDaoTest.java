package com.es.phoneshop.model.product;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

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
        productDao.getProduct((long) 100);
    }

    @Test
    public void testGetProductWithValidID() {
        assertThat(productDao.getProduct((long) 5).equals(productDao.getProduct((long) 5)), is(true));
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
        productDao.delete((long) 5);
        assertThat(productDao.findProducts().size(), is(sizeBeforeDelition - 1));
    }

    @Test(expected = RuntimeException.class)
    public void testDeliteWithInvalidID() {
        productDao.delete((long) 100);
    }
}


