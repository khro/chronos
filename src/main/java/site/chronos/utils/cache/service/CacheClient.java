package site.chronos.utils.cache.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.CacheManager;

import redis.clients.jedis.JedisPubSub;

public interface CacheClient {

    String getKeyVersion();

    /*------------------ String 相关操作方法--------------------*/
    <T> boolean set(String key, T value);

    boolean set(String key, byte[] value);

    <T> boolean set(String key, int seconds, T value);

    boolean set(String key, int seconds, byte[] value);

    <T> T get(String key);

    Long getCount(String key);

    byte[] getBytes(String key);

    <T> List<T> batchGet(String[] keys);

    boolean delete(String key);

    List<Boolean> batchDelete(String[] keys);

    boolean exists(String key);

    boolean expire(String key, int expire);

    List<String> keys(String like);

    Long ttl(String key);

    Long llen(String key);

    Long incr(String key);

    Long decrBy(String key, long value);

    /*------------------  List相关操作 方法--------------------*/
    <T> boolean lpush(String key, T value);

    boolean lpush(String key, byte[] value);

    <T> boolean rpush(String key, T value);

    boolean rpush(String key, byte[] value);

    <T> T lpop(String key);

    byte[] lpopBytes(String key);

    <T> T rpop(String key);

    byte[] rpopBytes(String key);

    <T> List<T> lrange(String key, int start, int end);

	/*------------------ set 相关操作方法--------------------*/

    <T> boolean sadd(String key, T value);

    Long scard(String key);

    <T> T spop(String key);

    <T>boolean sismember(String key, T member);

    <T> Set<T> smembers(String key);

/*    <T>boolean smove(String srckey, String dstkey, T member);

    <T>boolean srem(String key, T... members);

    <T> Set<T> sinter(String... keys);

    <T>boolean sinterstore(String dstkey, T... keys);

    <T> Set<T> sunion(String... keys);

    <T>boolean sunionstore(String dstkey, T... keys);

    <T> Set<T> sdiff(String... keys);

    <T>boolean sdiffstore(String dstkey, T... keys);

    <T> T srandmember(String key);

    <T> List<T> srandmember(String key, int count);*/

	/*------------------ zset 相关操作方法--------------------*/

/*    <T> boolean zadd(String key, double score, T member);

    <T> boolean zadd(String key, Map<T, Double> scoreMembers);

    <T> Set<T> zrange(String key, long start, long end);

    <T> boolean zrem(String key, T... member);

    <T> Double zincrby(String key, double score, T member);

    <T>Long zrank(String key,T member);

    <T>Long zrevrank(String key,T member);

    <T> Set<T> zrevrange(String key, long start, long end);

    Long zcard(String key);

    <T>Double zscore(String key, T member);*/

    /*------------------ hash 相关操作方法--------------------*/
    <T> boolean hset(String key, String field, T value);

    <T> T hget(String key, String field);
    
    Long hgetCount(String key,String field);

    <T> List<T> hmget(String key, String... fields);

    <T> Map<String, T> hgetAll(String key);

    boolean hexists(String key, String field);

    Set<String> hkeys(String key);

    boolean hdel(String key, String... fields);
    
    Long hincrBy(String key, String field,long value);

    /*------------------ Pub/Sub 相关操作方法--------------------*/
    <T> boolean publish(String channel, T message);

    void psubscribe(JedisPubSub jedisPubSub, String[] patterns);

    /*------------------ clear cache--------------------*/
    void flushAll();


}
