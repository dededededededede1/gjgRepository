package com.bobo.utils.jedis;

import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {

  private JedisUtil() {
  }

  JedisPool jedisPool = RedisFactory.getJedisPool();

  /**
   * 缓存生存时间
   */
  private final int expire = 60000;

  private static final JedisUtil jedisUtil = new JedisUtil();

  /**
   * 从jedis连接池中获取获取jedis对象
   *
   * @return
   */
  public Jedis getJedis() {
    return jedisPool.getResource();
  }

  /**
   * 获取JedisUtil实例
   *
   * @return
   */
  public static JedisUtil getInstance() {
    return jedisUtil;
  }

  /**
   * 回收jedis(放到finally中)
   *
   * @param jedis
   */
  public void returnJedis(Jedis jedis) {
    if (null != jedis) {
      jedis.close();
    }
  }

  /**
   * 销毁连接(放到catch中)
   *
   * @param
   * @param
   */
  public static void returnBrokenResource(Jedis jedis) {
    if (null != jedis) {
      jedis.close();
    }
  }

  /**
   * 设置过期时间
   *
   * @param key
   * @param seconds
   * @author ruan 2013-4-11
   */
  public void expire(String key, int seconds) {
    if (seconds <= 0) {
      return;
    }
    Jedis jedis = getJedis();
    jedis.expire(key, seconds);
    returnJedis(jedis);
  }

  /**
   * 设置默认过期时间
   *
   * @param key
   * @author ruan 2013-4-11
   */
  public void expire(String key) {
    expire(key, expire);
  }

  /**
   * 清空所有key
   */
  public String flushAll() {
    Jedis jedis = getJedis();
    String stata = jedis.flushAll();
    returnJedis(jedis);
    return stata;
  }

  /**
   * 根据key获取记录
   *
   * @param key String
   * @return 值
   */
  public String get(String key) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    String value = sjedis.get(key);
    returnJedis(sjedis);
    return value;
  }
  
  /**
   * 添加记录,如果记录已存在将覆盖原有的value
   *
   * @param key   String
   * @param value String
   * @return 状态码
   */
  public String set(String key, String value) {
    Jedis jedis = getJedis();
    String status = jedis.set(key, value);
    returnJedis(jedis);
    return status;
  }

  /**
   * 添加有过期时间的记录
   *
   * @param key     String
   * @param seconds int过期时间，以秒为单位
   * @param value   String
   * @return String 操作状态
   */
  public String setEx(String key, int seconds, String value) {
    Jedis jedis = getJedis();
    String str = jedis.setex(key, seconds, value);
    returnJedis(jedis);
    return str;
  }

  /**
   * 获取并设置指定key对应的value
   * 
   * 如果key存在返回之前的value,否则返回null
   *
   * @param key   String
   * @param value String
   * @return String 原始value或null
   */
  public String getSet(String key, String value) {
    Jedis jedis = getJedis();
    String str = jedis.getSet(key, value);
    returnJedis(jedis);
    return str;
  }

  /**
   * 在指定的key中追加value
   *
   * @param key   String
   * @param value String
   * @return long 追加后value的长度
   **/
  public long append(String key, String value) {
    Jedis jedis = getJedis();
    long len = jedis.append(key, value);
    returnJedis(jedis);
    return len;
  }

  /**
   * 获取key对应的值的长度
   *
   * @param key String
   * @return value值得长度
   */
  public long strlen(String key) {
    Jedis jedis = getJedis();
    long len = jedis.strlen(key);
    returnJedis(jedis);
    return len;
  }

  /**
   * 删除keys对应的记录,可以是多个key
   *
   * @param keys String...
   * @return 删除的记录数
   */
  public long del(String... keys) {
    Jedis jedis = getJedis();
    long count = jedis.del(keys);
    returnJedis(jedis);
    return count;
  }

  /**
   * 向List左侧追加记录
   *
   * @param key   String
   * @param value String
   * @return 记录总数
   */
  public long lpush(String key, String value) {
    Jedis jedis = getJedis();
    long count = jedis.lpush(key, value);
    returnJedis(jedis);
    return count;
  }

  /**
   * 向List右侧追加记录
   *
   * @param key   String
   * @param value String
   * @return 记录总数
   */
  public long rpush(String key, String value) {
    Jedis jedis = getJedis();
    long count = jedis.rpush(key, value);
    returnJedis(jedis);
    return count;
  }

  /**
   * List长度
   *
   * @param key String
   * @return 长度
   */
  public long llen(String key) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    long count = sjedis.llen(key);
    returnJedis(sjedis);
    return count;
  }

  /**
   * 获取指定范围的记录，可以做为分页使用
   *
   * @param key   String
   * @param start long
   * @param end   long
   * @return List
   */
  public List<String> lrange(String key, long start, long end) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    List<String> list = sjedis.lrange(key, start, end);
    returnJedis(sjedis);
    return list;
  }

  /**
   * 将List中的第一条记录移出List
   *
   * @param key String
   * @return 移出的记录
   */
  public String lpop(String key) {
    Jedis jedis = getJedis();
    String value = jedis.lpop(key);
    returnJedis(jedis);
    return value;
  }

  /**
   * 将List中最后第一条记录移出List
   *
   * @param key byte[]
   * @return 移出的记录
   */
  public String rpop(String key) {
    Jedis jedis = getJedis();
    String value = jedis.rpop(key);
    returnJedis(jedis);
    return value;
  }

  /**
   * 添加一个对应关系
   *
   * @param key   String
   * @param fieid String
   * @param value String
   * @return 状态码 1成功，0失败，fieid已存在将更新，也返回0
   **/
  public long hset(String key, String fieid, String value) {
    Jedis jedis = getJedis();
    long s = jedis.hset(key, fieid, value);
    returnJedis(jedis);
    return s;
  }

  /**
   * 返回hash中指定存储位置的值
   *
   * @param key   String
   * @param fieid String存储的名字
   * @return 存储对应的值
   */
  public String hget(String key, String fieid) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    String s = sjedis.hget(key, fieid);
    returnJedis(sjedis);
    return s;
  }

  /**
   * 以Map的形式返回hash中的存储和值
   *
   * @param key String
   * @return Map<Strinig, String>
   */
  public Map<String, String> hgetAll(String key) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    Map<String, String> map = sjedis.hgetAll(key);
    returnJedis(sjedis);
    return map;
  }

  /**
   * 获取hash中存储的个数，类似Map中size方法
   *
   * @param key String
   * @return long 存储的个数
   */
  public long hlen(String key) {
    // ShardedJedis sjedis = getShardedJedis();
    Jedis sjedis = getJedis();
    long len = sjedis.hlen(key);
    returnJedis(sjedis);
    return len;
  }
}
