package com.target.myretail.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.target.myretail.Model.ItemPrice;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullProduct {

    @JsonProperty("id")
    @ApiModelProperty(notes = "The Product ID , common between DB and External API")
    private String productId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "The Product Name from External API")
    private String name;

    @JsonProperty("current_price")
    @ApiModelProperty(notes = "The Pricing Info from DB")
    private ItemPrice itemPrice;

    @JsonProperty("error_message")
    private String errorMessage;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemPrice getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(ItemPrice itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "FullProduct{" +
                "prodcutId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", itemPrice=" + itemPrice +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
