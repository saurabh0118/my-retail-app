package com.target.myretail.Repository;

import org.springframework.stereotype.Repository;

import com.target.myretail.Model.ItemPrice;

@Repository
public interface ProductRepository {

    ItemPrice getPrice(String tcin);

    ItemPrice updatePrice(ItemPrice itemPrice, String tcin);
}