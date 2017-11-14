package com.target.myretail.Service;

import static com.target.myretail.Common.MyRetailConstants.ERROR_OCCURED_GET;
import static com.target.myretail.Common.MyRetailConstants.SUCCESS_UPDATE_PRICE;
import static com.target.myretail.Common.MyRetailConstants.ERROR_OCCURED_UPDATE_PRICE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.target.myretail.Common.ErrorMessages;
import com.target.myretail.Model.FullProduct;
import com.target.myretail.Model.ItemPrice;
import com.target.myretail.Model.ProductInfo;
import com.target.myretail.Repository.ProductRepository;

@Service
public class ProductInfoService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDescriptionService descriptionService;

    RestTemplate restTemplate = new RestTemplate();


    public FullProduct getProductDetails (String id) {

        FullProduct fullProduct = new FullProduct();

        if (id == null) {
            fullProduct.setErrorMessage(ErrorMessages.getInstance().getErrorMessage("404"));
        }
        else {
            // Call the external API to get Product id and Name
            ProductInfo productInfo = descriptionService.getProductDescription(id);

            if (productInfo.getErrorMessage() != null) {
                fullProduct.setErrorMessage(ErrorMessages.getInstance().getErrorMessage(productInfo.getErrorMessage()));
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
                    fullProduct.setErrorMessage(ERROR_OCCURED_GET);
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
            outputWUpdatedPrice.setErrorMessage(SUCCESS_UPDATE_PRICE);
        }
        else {
            outputWUpdatedPrice.setProductId(productId);
            outputWUpdatedPrice.setErrorMessage(ERROR_OCCURED_UPDATE_PRICE);
        }

        return outputWUpdatedPrice;

    }

}
