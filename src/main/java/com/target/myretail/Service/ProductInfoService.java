package com.target.myretail.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.target.myretail.Model.FullProduct;
import com.target.myretail.Model.ItemPrice;
import com.target.myretail.Model.ProductInfo;
import com.target.myretail.Repository.ProductRepository;

@Service
public class ProductInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoService.class);

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDescriptionService descriptionService;

    RestTemplate restTemplate = new RestTemplate();


    public FullProduct getProductDetails (String id) {

        FullProduct fullProduct = new FullProduct();

        if (id == null) {
            fullProduct.setErrorMessage("404 - Not Found");
        }
        else {
            // Call the external API to get Product id and Name
            ProductInfo productInfo = descriptionService.getProductDescription(id);

            if (productInfo.getErrorMessage() != null) {
                if (productInfo.getErrorMessage().contains("403")) {
                    productInfo.setErrorMessage("403 Forbidden: This item cannot be retrieved via this guest-facing endpoint state");
                }
                if (productInfo.getErrorMessage().contains("404")) {
                    productInfo.setErrorMessage("404 Not Found: This item was not found in the target API for product details");
                }
                fullProduct.setErrorMessage(productInfo.getErrorMessage());
            }
            else {
                //If the API returns data for the Product, get the price details from DB
                ItemPrice itemPriceDetails = getProductPrice(productInfo);

                ItemPrice itemPriceResponse = new ItemPrice();
                //Combine Product description and price (from API and DB) into a single response
                if(itemPriceDetails != null) {
                    fullProduct.setProductId(productInfo.getProduct().getItem().getTcin());
                    fullProduct.setName(productInfo.getProduct().getItem().getProduct_description().getTitle());
                    itemPriceResponse.setPrice(itemPriceDetails.getPrice());
                    itemPriceResponse.setCurrency(itemPriceDetails.getCurrency());
                    fullProduct.setItemPrice(itemPriceResponse);
                }
                else {
                    fullProduct.setProductId(productInfo.getProduct().getItem().getTcin());
                    fullProduct.setName(productInfo.getProduct().getItem().getProduct_description().getTitle());
                    fullProduct.setErrorMessage("ERROR: Item Price information is not available.");
                }

            }

        }
        return fullProduct;
    }


    //Repository Call to get the price information of the product from the DB
    public ItemPrice getProductPrice (ProductInfo productInfo) {

        String tcin = productInfo.getProduct().getItem().getTcin();
        ItemPrice itemPrice = productRepository.getPrice(tcin);
        return itemPrice;
    }

    //Call repository to update the price information of the product into DB
    public FullProduct updateProductPrice (ItemPrice newItemPrice, String productId) {

        ItemPrice itemPrice = productRepository.updatePrice(newItemPrice, productId);
        FullProduct outputWUpdatedPrice = new FullProduct();
        if(itemPrice != null){
            outputWUpdatedPrice.setProductId(productId);
            outputWUpdatedPrice.setItemPrice(itemPrice);
            outputWUpdatedPrice.setErrorMessage("Successfully updated Product Price Information");
        }
        else {
            outputWUpdatedPrice.setProductId(productId);
            outputWUpdatedPrice.setErrorMessage("ERROR Unable to update Product Price Information");
        }

        return outputWUpdatedPrice;

    }

}
