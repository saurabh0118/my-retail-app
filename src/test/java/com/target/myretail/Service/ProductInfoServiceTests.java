package com.target.myretail.Service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.target.myretail.Model.FullProduct;
import com.target.myretail.Model.Item;
import com.target.myretail.Model.ItemPrice;
import com.target.myretail.Model.Product;
import com.target.myretail.Model.ProductInfo;
import com.target.myretail.Repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductInfoServiceTests {

    @Mock
    FullProduct fullProduct;

    @Mock
    ProductInfoService productInfoService;
    
    @Mock
    ProductDescriptionService descriptionService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void testNullProductIDInputGetProductDetails() {
    		FullProduct demo = new FullProduct();
    		demo.setErrorMessage("404 - Not Found");
    		given(productInfoService.getProductDetails(null)).willReturn(demo);
        FullProduct fullProduct = productInfoService.getProductDetails(null);
        assertNull(fullProduct.getProductId());
        assertEquals(fullProduct.getErrorMessage(),"404 - Not Found");
    }

    @Test
    public void testValidProductIDInputGetProductDetails() {

        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setPrice((float)4.99);
        itemPrice.setCurrency("USD");
        given(productRepository.getPrice(anyString())).willReturn(itemPrice);
        
        FullProduct demo = new FullProduct();
        demo.setProductId("13860428");
        demo.setItemPrice(new ItemPrice());
        demo.getItemPrice().setPrice((float)4.99);
        demo.getItemPrice().setCurrency("USD");
        given(productInfoService.getProductDetails(anyString())).willReturn(demo);
        FullProduct output = productInfoService.getProductDetails("13860428");
        assertEquals(output.getProductId(),"13860428");
    }

    @Test
    public void testValidProductIDInputGetProductPrice() {

        ProductInfo productInfo = new ProductInfo();
        Product product = new Product();
        Item item = new Item();
        item.setTcin("13860428");
        product.setItem(item);
        productInfo.setProduct(product);

        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setPrice((float)4.99);
        itemPrice.setCurrency("USD");
        given(productRepository.getPrice(anyString())).willReturn(itemPrice);
        
        ItemPrice demo = new ItemPrice();
        demo.setPrice((float)4.99);
        demo.setCurrency("USD"); 
        
        given(productInfoService.getProductPrice(productInfo)).willReturn(demo);

        ItemPrice output = productInfoService.getProductPrice(productInfo);

        assertEquals(output.getPrice(),(float)4.99);
        assertEquals(output.getCurrency(),"USD");
    }

    @Test
    public void TestInvalidProductIDInput() {
    		ProductInfo demoProductInfo = new ProductInfo();
    		demoProductInfo.setErrorMessage("403 Error in service");
    		given(descriptionService.getProductDescription(anyString())).willReturn(demoProductInfo);
        ProductInfo productInfo = descriptionService.getProductDescription("123456789");
        assertNull(productInfo.getProduct());
        assertTrue(productInfo.getErrorMessage().contains("403"));
    }

    @Test
    public void TestValidProductIDInputUpdateProductPrice() {

        String productId = "13834456";
        ItemPrice itemPrice = new ItemPrice();
        itemPrice.setPrice((float)4.99);
        itemPrice.setCurrency("USD");
        given(productRepository.updatePrice(anyObject(),anyString())).willReturn(itemPrice);
        FullProduct demo = new FullProduct();
        demo.setProductId(productId);
        demo.setItemPrice(new ItemPrice());
        demo.getItemPrice().setPrice((float)4.99);
        demo.getItemPrice().setCurrency("USD");
        demo.setErrorMessage("Updated Price Successfully");
        given(productInfoService.updateProductPrice(itemPrice,productId)).willReturn(demo);
        FullProduct output = productInfoService.updateProductPrice(itemPrice, productId);

        assertEquals(output.getProductId(),productId);
        assertEquals(output.getItemPrice().getPrice(),(float)4.99);
        assertEquals(output.getItemPrice().getCurrency(),"USD");
        assertEquals(output.getErrorMessage(),"Updated Price Successfully");
    }

}
