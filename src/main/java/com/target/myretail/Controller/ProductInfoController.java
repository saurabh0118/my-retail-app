package com.target.myretail.Controller;

import static com.target.myretail.Common.MyRetailConstants.SWAGGER_API_NAME;
import static com.target.myretail.Common.MyRetailConstants.SWAGGER_API_DESC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.target.myretail.Model.FullProduct;
import com.target.myretail.Model.ItemPrice;
import com.target.myretail.Service.ProductInfoService;

import io.swagger.annotations.Api;


@CrossOrigin
@RestController
@RequestMapping("/products/v1")
@Api(value=SWAGGER_API_NAME, description=SWAGGER_API_DESC)

public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    /**
     * GET Method to get detailed information about product
     */
    @ResponseBody
    @RequestMapping (value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public FullProduct getProductData (@PathVariable("id") String id) {

        FullProduct fullProduct = productInfoService.getProductDetails(id);

        return fullProduct;

    }

    /**
     * PUT Method to update the price information for a product.
     */
    @ResponseBody
    @RequestMapping (value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public FullProduct updateProductData (@PathVariable("id") String id,@RequestBody FullProduct product) {

        ItemPrice newItemPrice = new ItemPrice();
        newItemPrice.setPrice(product.getItemPrice().getPrice());
        newItemPrice.setCurrency(product.getItemPrice().getCurrency());
        FullProduct updatePriceResponse = productInfoService.updateProductPrice(newItemPrice, id);
        updatePriceResponse.setName(product.getName());
        return updatePriceResponse;
    }


}
