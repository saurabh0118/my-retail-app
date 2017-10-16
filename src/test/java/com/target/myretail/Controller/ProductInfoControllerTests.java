package com.target.myretail.Controller;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.Model.FullProduct;
import com.target.myretail.Model.ItemPrice;
import com.target.myretail.Service.ProductInfoService;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductInfoController.class)
public class ProductInfoControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductInfoService productInfoService;
    
    /**
	 * Setup for Mockito before any test run.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void testGetProductInfo() throws Exception {

        FullProduct fullProduct = new FullProduct();
        fullProduct.setProductId("13860428");
        fullProduct.setErrorMessage(null);

        given(productInfoService.getProductDetails(anyString())).willReturn(fullProduct);

        mvc.perform(MockMvcRequestBuilders.get("/products/v1/13860428").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", equalTo("13860428")))
                .andExpect(jsonPath("$.error_message", equalTo(null)));

    }


    @Test
    public void testProductNotFound() throws Exception {

        FullProduct fullProduct = new FullProduct();

        fullProduct.setErrorMessage("403 Forbidden: You do not have access");

        given(productInfoService.getProductDetails(anyString())).willReturn(fullProduct);

        mvc.perform(MockMvcRequestBuilders.get("/products/v1/15117729").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", equalTo(null)))
                .andExpect(jsonPath("$.error_message", equalTo("403 Forbidden: You do not have access")));

    }

    @Test
    public void testProductInputNotProvided() throws Exception {

        FullProduct fullProduct = new FullProduct();

        fullProduct.setErrorMessage("404 - Page not found");

        given(productInfoService.getProductDetails(anyString())).willReturn(fullProduct);

        mvc.perform(MockMvcRequestBuilders.get("/products/v1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testUpdateProductPrice() throws Exception {

        FullProduct updatedPriceResponse = new FullProduct();
        updatedPriceResponse.setProductId("13860428");
        updatedPriceResponse.setName("13860428");
        ItemPrice updatedPrice = new ItemPrice();
        updatedPrice.setPrice((float)5.99);
        updatedPrice.setCurrency("USD");
        updatedPriceResponse.setItemPrice(updatedPrice);
        updatedPriceResponse.setErrorMessage("Updated Price Successfully");

        given(productInfoService.updateProductPrice(anyObject(),anyString())).willReturn(updatedPriceResponse);

        mvc.perform(MockMvcRequestBuilders.put("/products/v1/13860428").content(asJsonString(updatedPriceResponse)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo("13860428")))
                .andExpect(jsonPath("$.error_message", equalTo("Updated Price Successfully")))
                .andExpect(jsonPath("$.current_price.value", equalTo(5.99)))
                .andExpect(jsonPath("$.current_price.currency_code", equalTo("USD")));

    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
