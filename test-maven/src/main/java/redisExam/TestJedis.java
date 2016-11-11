package redisExam;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class TestJedis {

	private static final Logger LOGGER = Logger.getLogger(TestJedis.class);
	private Jedis jedis = null;
	JedisFactory jedisFactory = null;

	public TestJedis() {
		this.jedis = JedisFactory.getInstance().getJedisPool().getResource();
	}

	public void push() {

		try {

			LOGGER.info("Server is running: " + jedis.ping());
			if (jedis.isConnected()) {
				LOGGER.info("connected");
			}
			jedis.set("test", "set");

		} catch (JedisConnectionException jex) {
			LOGGER.info("Got jedis conncetion exception ", jex);
			try {
				JedisFactory.getInstance().getJedisPool().returnBrokenResource(jedis);
			} catch (Exception e) {
				LOGGER.info("jedis returnBrokenResource Exception**********", e);
			}

		} finally {
			try {
				if (null != jedis)
					JedisFactory.getInstance().getJedisPool().returnResource(jedis);
			} catch (Exception e) {
				LOGGER.info("jedis  returnResource Exception**********", e);
			}

		}
	}
}