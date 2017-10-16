package com.target.myretail.Repository;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.target.myretail.Model.ItemPrice;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private static final String KEY = "ItemPrice";
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Autowired
    @Qualifier("itemRedisTemplate")
    private  RedisTemplate<String, Map<String,ItemPrice>> redisTemplate;

    /**
     * GET price details from DB
     */
    public ItemPrice getPrice(String tcin) {
        String hashKey = tcin;
        Object itemPrice = redisTemplate.opsForHash().get(KEY, hashKey);
        LOGGER.info("Item details: " + itemPrice);
        return itemPrice != null ? (ItemPrice) itemPrice : null;
    }

    /**
     * Update price information into DB
     */
    public ItemPrice updatePrice(ItemPrice itemPrice, String tcin) {
        redisTemplate.opsForHash().put(KEY, tcin, itemPrice);
        LOGGER.info("Update Item for price[" + itemPrice + "] success!");
        LOGGER.info("User Hash size is : " + redisTemplate.opsForHash().size(KEY));
        ItemPrice updatedPrice = getPrice(tcin);
        return updatedPrice;
    }
}
