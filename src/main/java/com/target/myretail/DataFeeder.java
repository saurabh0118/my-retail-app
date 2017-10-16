package com.target.myretail;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.target.myretail.Model.ItemPrice;
import com.target.myretail.Model.ProductInfo;
import com.target.myretail.Repository.ProductRepositoryImpl;

//@SpringBootApplication
public class DataFeeder {
	private static RedisTemplate<String, Map<String, ProductInfo>> redisTemplate;
	private static String key = "ItemPrice";
	public static void main(String[] args) {
		final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
		LOGGER.info("-----------Starting Redis hash testing-----------");
		ApplicationContext ctx = SpringApplication.run(MyRetailApplication.class, args);
		RedisConnectionFactory connectionFactory = ctx
				.getBean(RedisConnectionFactory.class);
		redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
		redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate
                .setHashValueSerializer(new Jackson2JsonRedisSerializer<ItemPrice>(
                        ItemPrice.class));
		redisTemplate.afterPropertiesSet();

		DataFeeder hashTest = ctx.getBean(DataFeeder.class);
	
        hashTest.batchInsert();
        hashTest.findAll();
	}

	public void batchInsert() {
        final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
        Map<String, ItemPrice> items = new HashMap<>();
        String tcin;
        for (int i = 20; i <= 35; i++) { // Checked few Product numbers between 13860420 and 13860435 as Available
            ItemPrice info = new ItemPrice();
            info.setCurrency("USD");
            info.setPrice((float) (0.99));
            tcin = "138604"+i;
            items.put(tcin, info);
        }
        redisTemplate.delete(key);  // Flush Data
        redisTemplate.opsForHash().putAll(key, items);
        LOGGER.info("batchInsert Items[" + items + "] success!");
        LOGGER.info("User Hash size is : " + redisTemplate.opsForHash().size(key));
    }

    public void findAll() {
        Map<Object, Object> items = redisTemplate.opsForHash().entries(key);
        final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
        LOGGER.info("All Items[" + items + "]");
        LOGGER.info("findAll User size is : " + items.size());
    }
    
}   