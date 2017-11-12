package com.target.myretail.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import com.target.myretail.Model.ItemPrice;

import static com.target.myretail.Common.MyRetailConstants.REPOSITORY_PACKAGE;
import static com.target.myretail.Common.MyRetailConstants.REDIS_URL;
import static com.target.myretail.Common.MyRetailConstants.ITEM_REDIS_TEMPLATE;


@Configuration
@EnableRedisRepositories(basePackages = REPOSITORY_PACKAGE)
public class ApplicationConfiguration {

	@Autowired
	private Environment env;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		URI redisUri;

		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		try {
			redisUri = new URI(env.getProperty(REDIS_URL));
			redisConnectionFactory.setHostName(redisUri.getHost());
			redisConnectionFactory.setPort(redisUri.getPort());
			redisConnectionFactory
					.setPassword(redisUri.getUserInfo() != null ? redisUri.getUserInfo().split(":", 2)[1] : "");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return redisConnectionFactory;
	}

	@Bean(name = ITEM_REDIS_TEMPLATE)
	public RedisTemplate<String, Map<String, ItemPrice>> redisTemplate() {
		RedisTemplate<String, Map<String, ItemPrice>> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(template.getStringSerializer());
		template.setHashKeySerializer(template.getStringSerializer());
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<ItemPrice>(ItemPrice.class));
		template.afterPropertiesSet();
		return template;
	}

}