package redisexam;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * http://ilovehsk.tistory.com/174
 * @author user
 *
 */
public class RedisTests {

     public static void main(String[] args) throws Exception {

          Set<String> sentinelIps = new HashSet();
          sentinelIps.add("175.207.8.166:26379");
          sentinelIps.add("175.207.8.167:26379");
          sentinelIps.add("175.207.8.168:26379");

          GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

          JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentinelIps, poolConfig, 5000, "1020");
          Jedis jedis1 = jedisSentinelPool.getResource();
          String[] keys = new String[100];
         
         
         
          //삭제처리
          StringBuffer delkey = new StringBuffer();
          for (int i = 0; i < 100; i++) {    
               keys[i] ="Test_"+i;
          }    
          jedis1.del(keys);
              

          for (int i = 0; i < 100; i++) {              
               try {
                    RedisTests.setData(jedisSentinelPool, "Test_"+i, "value_"+i);
                    System.out.println("input > key : Test_"+i+" , value :  value_"+i);
               } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("에러 발생");
               }         
              
               Thread.sleep(500);
          }
         
          for (int i = 0; i < 100; i++) {
               Jedis jedis = jedisSentinelPool.getResource();
               try {
                    String value = jedis.get("Test_" + i);
                    System.out.println("output > key : Test_"+i+" , value :"+value);
               } catch (Exception e) {
                    e.printStackTrace();
               } 
          }

          /*
          * JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "175.207.8.166",6379,0,"1020");
          * Jedis jedis = jedisPool.getResource();
          * try {
          * jedis.set("foo", "client_test");
          * } catch (Exception e) {
          * System.out.println(e.getMessage());
          * }
          * System.out.println(">>>>> : "+jedis.get("foo"));
          */

     }
     public  static void  setData(JedisSentinelPool jedisSentinelPool, String key , String value ){
          Jedis jedis =null;
          try {              
               jedis = jedisSentinelPool.getResource();
               jedis.set(key, value);
          } catch (Exception e) {
               System.out.println(e);
               
          } 
     }

}
