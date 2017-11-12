package com.target.myretail.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.target.myretail.Model.ProductInfo;


@Service
public class ProductDescriptionService {

	@Autowired
	private Environment env;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDescriptionService.class);

    RestTemplate restTemplate = new RestTemplate();


    // Call external API to get details of the product
    public ProductInfo getProductDescription (String id) {

        ProductInfo productInfo = new ProductInfo();
        String url = env.getProperty("target.product.api.prefix") + id + env.getProperty("target.product.api.suffix");
        LOGGER.info(url);
        try {
            productInfo = restTemplate.getForObject(url, ProductInfo.class);
        }
        catch (HttpClientErrorException ex){
            LOGGER.info("Exception:" + ex.toString());
            productInfo.setErrorMessage(ex.getMessage());
        }

        LOGGER.info("getProductDescription:" + productInfo.toString());
        return productInfo;
    }

}
