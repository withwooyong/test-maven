package redisExam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("119.149.188.226");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setTimeout(0);
		jedisConnectionFactory.setUsePool(true);

		return jedisConnectionFactory;
	}

	@Bean
	public StringRedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
		return stringRedisTemplate;
	}
}