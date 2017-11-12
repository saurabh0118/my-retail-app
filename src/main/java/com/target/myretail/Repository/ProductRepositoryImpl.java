package com.target.myretail.Repository;

import java.util.Map;
import static com.target.myretail.Common.MyRetailConstants.ITEM_PRICE_KEY;
import static com.target.myretail.Common.MyRetailConstants.ITEM_REDIS_TEMPLATE;
import static com.target.myretail.Common.MyRetailConstants.ITEM_DETAILS_LOG;
import static com.target.myretail.Common.MyRetailConstants.BATCH_LOG_SUCCESS_2;
import static com.target.myretail.Common.MyRetailConstants.UPDATE_LOG_SUCCESS;
import static com.target.myretail.Common.MyRetailConstants.ITEM_PRICE_LOG_1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.target.myretail.Model.ItemPrice;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private static final String KEY = ITEM_PRICE_KEY;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Autowired
    @Qualifier(ITEM_REDIS_TEMPLATE)
    private  RedisTemplate<String, Map<String,ItemPrice>> redisTemplate;

    /**
     * GET price details from DB
     */
    public ItemPrice getPrice(String tcin) {
        String hashKey = tcin;
        Object itemPrice = redisTemplate.opsForHash().get(KEY, hashKey);
        LOGGER.info(ITEM_DETAILS_LOG + itemPrice);
        return itemPrice != null ? (ItemPrice) itemPrice : null;
    }

    /**
     * Update price information into DB
     */
    public ItemPrice updatePrice(ItemPrice itemPrice, String tcin) {
        redisTemplate.opsForHash().put(KEY, tcin, itemPrice);
        LOGGER.info(UPDATE_LOG_SUCCESS + itemPrice + BATCH_LOG_SUCCESS_2);
        LOGGER.info(ITEM_PRICE_LOG_1 + redisTemplate.opsForHash().size(KEY));
        ItemPrice updatedPrice = getPrice(tcin);
        return updatedPrice;
    }
}
