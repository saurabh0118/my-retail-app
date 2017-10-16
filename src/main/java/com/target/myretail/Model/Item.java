package com.target.myretail.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {

    /**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 112390234523408L;

	@JsonProperty("tcin")
    private String tcin;

    @JsonProperty("dpci")
    private String dpci;

    @JsonProperty("product_description")
    private ProductDescription product_description;

    public String getTcin() {
        return tcin;
    }

    public void setTcin(String tcin) {
        this.tcin = tcin;
    }

    public String getDpci() {
        return dpci;
    }

    public void setDpci(String dpci) {
        this.dpci = dpci;
    }

    public ProductDescription getProduct_description() {
        return product_description;
    }

    public void setProduct_description(ProductDescription productDescription) {
        this.product_description = productDescription;
    }

    @Override
    public String toString() {
        return "Item{" +
                "tcin='" + tcin + '\'' +
                ", dpci='" + dpci + '\'' +
                ", product_description=" + product_description +
                '}';
    }
}
