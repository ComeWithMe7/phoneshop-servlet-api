package com.es.phoneshop.model.product;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArrayListProductDaoTest
{
    @InjectMocks
    private ArrayListProductDao productDao;

    @Mock
    private Product product;

    @Mock
    private List<Product> products;

    @Before
    public void setup()
    {
        productDao = new ArrayListProductDao();
    }

    @Test
    public void testFindProducts() {
        assertThat(productDao.findProducts().isEmpty(), is(false));
    }

}
