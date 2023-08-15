package com.bobo.utils.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisFactory {
 
  private static String host = "127.0.0.1";

  private static int port = 6379;

  private static int timeout = 5000;

  private static int maxActive = 200;

  private static int maxIdle = 200;

  private static int minIdle = 0;

  private static long maxWaitMillis = -1;

  private static JedisPool jedisPool;

  static {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(maxIdle);
    jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
    jedisPoolConfig.setMaxTotal(maxActive);
    jedisPoolConfig.setMinIdle(minIdle);
    jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, null);
  }

  public static JedisPool getJedisPool() {
    return jedisPool;
  }

}
