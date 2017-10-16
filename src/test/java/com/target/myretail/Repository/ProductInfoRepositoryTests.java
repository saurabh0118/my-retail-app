package com.target.myretail.Repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.target.myretail.MyRetailApplication;
import com.target.myretail.Model.ItemPrice;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyRetailApplication.class)

public class ProductInfoRepositoryTests {

    @MockBean
    private ProductRepositoryImpl repo;

    @Test
    public void testGetPriceValidInput() {

        String tcin = "13860428";

        ItemPrice expected = new ItemPrice();
        expected.setPrice((float)12.99);
        expected.setCurrency("USD");
        
        ItemPrice demo = new ItemPrice();
        demo.setPrice((float)12.99);
        demo.setCurrency("USD"); 
        
        given(repo.getPrice(anyString())).willReturn(demo);
        
        ItemPrice output = repo.getPrice(tcin);

        assertEquals(output.getPrice(),expected.getPrice());

    }

    @Test
    public void testUpdatePriceValidInput() {

        String tcin = "13860428";
        ItemPrice expected = new ItemPrice();
        expected.setPrice((float)12.99);
        expected.setCurrency("USD");
        
        ItemPrice demo = new ItemPrice();
        demo.setPrice((float)12.99);
        demo.setCurrency("USD"); 
        
        given(repo.updatePrice(expected, tcin)).willReturn(demo);

        ItemPrice output = repo.updatePrice(expected, tcin);

        assertEquals(output.getPrice(),expected.getPrice());
        assertEquals(output.getCurrency(), expected.getCurrency());

    }
}
