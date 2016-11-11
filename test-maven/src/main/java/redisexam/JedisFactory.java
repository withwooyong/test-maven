package redisexam;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

class JedisFactory {
	private static JedisPool jedisPool;
	private static JedisFactory instance = null;
	private static String REDIS_HOST = "119.149.188.226";
	
	
	public JedisFactory() {

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setTestOnBorrow(true);
		jedisPool = new JedisPool(poolConfig, REDIS_HOST, 6379);

	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public static JedisFactory getInstance() {
		if (instance == null) {
			instance = new JedisFactory();
		}
		return instance;
	}
}