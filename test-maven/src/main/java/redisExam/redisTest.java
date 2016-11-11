package redisExam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * root 계정 사용
 * [root@repaddev02 src]# pwd
 * /root/redis-3.2.4/src
 * [root@repaddev02 src]# ./redis-server ../redis.conf 
 * 
 * -- activeMQ
[root@repaddev02 ~]# wget http://archive.apache.org/dist/activemq/apache-activemq/5.9.0/apache-activemq-5.9.0-bin.tar.gz
[root@repaddev02 ~]# tar -xvzf apache-activemq-5.9.0-bin.tar.gz 
[root@repaddev02 bin]# ./activemq start
[root@repaddev02 bin]# ./activemq stop

 * @author user
 *
 */
public final class redisTest {
	
	private static JedisPool jedisPool;
	private static Jedis jedis;
	private static Jedis jedis2;
	
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("119.149.188.226");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setTimeout(0);
		jedisConnectionFactory.setUsePool(true);
		return jedisConnectionFactory;
	}
	
	public StringRedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
		stringRedisTemplate.afterPropertiesSet();
		return stringRedisTemplate;
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		redisTest test = new redisTest();
		//test.jedisTest();
		test.jedisFlushAll();
		//test.jedisGetTest();
		//test.redisTest();
		//test.redisGetTest();
		//test.redisSortGetTest();
		//test.redisDeleteTest();
	}
	
	public void redisDeleteTest() {
		JedisConnectionFactory jedisConnectionFactory = jedisConnectionFactory();
		jedisConnectionFactory.setHostName("119.149.188.226");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.afterPropertiesSet();
		RedisTemplate<String, String> template = redisTemplate(jedisConnectionFactory);		
		ValueOperations<String, String> valueOps = template.opsForValue();
		SetOperations<String, String> setOps = template.opsForSet();
		
		String key = "FFMPEG.COMMAND-fa268de6-3312-404e-9ebc-6e78f42d32fa";
		
		System.out.println(valueOps.get(key));
		Set<String> setKeys = setOps.getOperations().keys("FFMPEG.COMMAND-" + "*");
		System.out.println(setKeys.size());

		setOps.getOperations().delete(key);
		Set<String> setKeys2 = setOps.getOperations().keys("FFMPEG.COMMAND-" + "*");
		System.out.println(setKeys2.size());
		
	}
	public void redisSortGetTest() {
		JedisConnectionFactory jedisConnectionFactory = jedisConnectionFactory();
		jedisConnectionFactory.setHostName("119.149.188.226");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.afterPropertiesSet();
		RedisTemplate<String, String> template = redisTemplate(jedisConnectionFactory);
		
		ZSetOperations<String, String> opsForZSet = template.opsForZSet();		
		ValueOperations<String, String> valueOps = template.opsForValue();
		
		RedisOperations<String, String> redis = opsForZSet.getOperations();
		
        Set<String> setKeys = redis.keys("FFMPEG.COMMAND" + "*");

        
        
        List<String> keyList = new ArrayList(setKeys); // Set을 List로 변환        
        Collections.sort(keyList, Collections.reverseOrder()); // 역정렬
        System.out.println(keyList);
        
        List<String> valueList = valueOps.multiGet(keyList);
        System.out.println(valueList);
        // 결과 출력
        for (String value : valueList) {
        	System.out.println(value);
		}
		
		String[] strKeys = setKeys.toArray(new String[setKeys.size()]);
		
		for (int i = 0; i < strKeys.length; i++) {
			System.out.println(strKeys[i] + "=" + valueOps.get(strKeys[i].substring(strKeys[i].indexOf("FFMPEG.COMMAND" + "-"))));
			//System.out.println(valueOps.get(strKeys[i].substring(strKeys[i].indexOf("FFMPEG.COMMAND" + "-"))));
		}	
	}
	
	public void jedisGetTest() {
		
		jedis2 = new Jedis("119.149.188.226", 6379);
		String value = jedis2.get("FFMPEG.COMMAND-0cb6956a-dd7d-447a-a12d-749fede8e36d");
		System.out.println(value);
	}
	
	public void redisGetTest() throws JsonParseException, JsonMappingException, IOException {
		
		JedisConnectionFactory jedisConnectionFactory = jedisConnectionFactory();
		jedisConnectionFactory.setHostName("119.149.188.226");
		jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.afterPropertiesSet();
		RedisTemplate<String, String> template = redisTemplate(jedisConnectionFactory);
		
		ValueOperations<String, String> valueOps = template.opsForValue();
		ListOperations<String, String> listOps = template.opsForList();
		
		RedisOperations<String, String> redis = listOps.getOperations();        
        Set<String> setKeys = redis.keys("FFMPEG.COMMAND" + "*");
		
		String[] strKeys = setKeys.toArray(new String[setKeys.size()]);
		
		for (int i = 0; i < strKeys.length; i++) {
			System.out.println(strKeys[i].substring(strKeys[i].indexOf("FFMPEG.COMMAND" + "-")));
			System.out.println(valueOps.get(strKeys[i].substring(strKeys[i].indexOf("FFMPEG.COMMAND" + "-"))));
		}		
		
	}
	
	public void redisTest() {
		
		/**
		 * <bean id="jedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory"
                p:use-pool="true"
                p:host-name="172.20.44.209" p:port="6379"       
         />
		 */
		JedisConnectionFactory jedisConnectionFactory = jedisConnectionFactory();
		RedisTemplate<String, String> template = redisTemplate(jedisConnectionFactory);
		template.afterPropertiesSet();
		ValueOperations<String, String> valueOps = template.opsForValue();
		
		valueOps.set("testKey1", "testValue1");
		valueOps.set("testKey2", "testValue2");
		valueOps.set("testKey3", "testValue3");
		
		System.out.println(valueOps.get("testKey3"));
		
		Set<String> setKeys = template.keys("FFMPEG*");
		
		String[] strKeys = setKeys.toArray(new String[setKeys.size()]);
		
		for (int i = 0; i < strKeys.length; i++) {
			valueOps.get(strKeys[i]);
		}
				
	}
	
	public static void jedisFlushAll() {
		jedis = new Jedis("119.149.188.226", 6379);
		System.out.println(jedis.dbSize());		
		jedis.flushAll();
		System.out.println(jedis.dbSize());		
	}
	
	public static void jedisTest() {
		jedis2 = new Jedis("119.149.188.226", 6379);
		
//		jedis.keys("*");
		jedis2.set("testKey1", "testValue1");
		jedis2.set("testKey2", "testValue2");
		jedis2.set("testKey3", "testValue3");
		System.out.println(jedis2.dbSize());		
		System.out.println(jedis2.keys("*"));
		
		//jedis2.flushAll();
		
		//System.out.println("###" + jedis.get("ffmpeg_0008bcdd-204a-4c8f-8624-ab45d87f1882"));
		
		Set<String> setKeys = jedis2.keys("*");
		String[] strKeys = setKeys.toArray(new String[setKeys.size()]);
		List<String> listValues = new ArrayList<String>();		
		listValues = jedis2.mget(strKeys);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		for (int i = 0; i < strKeys.length; i++) {
			//dataMap.put(strKeys[i], listValues.get(i));
			System.out.println(strKeys[i] + ":" + listValues.get(i));
		}
		
		//jedisPool.destroy();
	}

}