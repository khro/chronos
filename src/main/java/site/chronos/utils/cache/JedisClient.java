//package site.chronos.utils.cache;
//
//import com.txframework.util.serialize.SerializeUtils;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPubSub;
//import redis.clients.jedis.Pipeline;
//import redis.clients.util.SafeEncoder;
//import site.chronos.utils.cache.service.CacheClient;
//
//import java.util.*;
//
//import org.apache.commons.lang3.StringUtils;
//
///**
// * @author wulg
// * @description Jedis缓存客户端实现
// */
//@SuppressWarnings("unchecked")
//public class JedisClient implements CacheClient {
//
//    private JedisPool jedisPool;//jedis连接池
//
//    private String version;//缓存key版本号
//
//    public void setJedisPool(JedisPool jedisPool) {
//        this.jedisPool = jedisPool;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
//
//    protected Jedis getResource() {
//        return jedisPool.getResource();
//    }
//
//    protected void returnResource(Jedis resource, boolean broken) {
//        if (broken) {
//            jedisPool.returnBrokenResource(resource);
//        } else {
//            jedisPool.returnResource(resource);
//        }
//    }
//
//    protected String buildKeyByVersion(String key) {
//        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(this.version)) {
//            if (!key.startsWith(this.version + "_")) {//判断是否带版本前缀
//                return this.version + "_" + key;
//            }
//        }
//        return key;
//    }
//
//    @Override
//    public String getKeyVersion() {
//        return this.version;
//    }
//
//
//    @Override
//    public <T> boolean set(String key, T value) {
//        byte[] valueBytes = SerializeUtils.serialize(value);
//        return set(key, valueBytes);
//    }
//
//    @Override
//    public boolean set(String key, byte[] value) {
//        boolean broken = false;
//        String result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.set(SafeEncoder.encode(buildKeyByVersion(key)), value);
//        } catch (Exception e) {
//            broken = true;
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return "OK".equals(result) ? true : false;
//    }
//
//    @Override
//    public <T> boolean set(String key, int seconds, T value) {
//        byte[] valueBytes = SerializeUtils.serialize(value);
//        return set(key, seconds, valueBytes);
//    }
//
//    @Override
//    public boolean set(String key, int seconds, byte[] value) {
//        boolean broken = false;
//        String result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.setex(SafeEncoder.encode(buildKeyByVersion(key)), seconds, value);
//        } catch (Exception e) {
//            broken = true;
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return "OK".equals(result) ? true : false;
//    }
//
//    @Override
//    public <T> T get(String key) {
//        byte[] valueBytes = getBytes(key);
//        if (valueBytes == null) {
//            return null;
//        }
//        return (T) SerializeUtils.deserialize(valueBytes);
//    }
//
//    @Override
//    public Long getCount(String key) {
//        byte[] valueBytes = getBytes(key);
//        if (valueBytes == null) {
//            return null;
//        }
//        return Long.valueOf(SafeEncoder.encode(valueBytes));
//    }
//
//    @Override
//    public byte[] getBytes(String key) {
//        boolean broken = false;
//        byte[] result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.get(SafeEncoder.encode(buildKeyByVersion(key)));
//        } catch (Exception e) {
//            broken = true;
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public <T> List<T> batchGet(final String[] keys) {
//        boolean broken = false;
//        Jedis jedis = getResource();
//        List<Object> plList = null;
//        try {
//            Pipeline pipeline = jedis.pipelined();
//            for (String key : keys) {
//                pipeline.get(SafeEncoder.encode(buildKeyByVersion(key)));
//            }
//            plList = pipeline.syncAndReturnAll();
//        } finally {
//            returnResource(jedis, broken);
//        }
//        List<T> resultList = new ArrayList<T>(plList.size());
//        for (Object object : plList) {
//            if (object == null) {
//                continue;
//            }
//            resultList.add((T) SerializeUtils.deserialize((byte[]) object));
//        }
//        return resultList;
//    }
//
//    @Override
//    public boolean delete(String key) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.del(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//
//        return result == 1l ? true : false;
//    }
//
//    @Override
//    public List<Boolean> batchDelete(final String[] keys) {
//        boolean broken = false;
//        Jedis jedis = getResource();
//        List<Object> plList = null;
//        try {
//            Pipeline pipeline = jedis.pipelined();
//            for (String key : keys) {
//                pipeline.del(SafeEncoder.encode(buildKeyByVersion(key)));
//            }
//            plList = pipeline.syncAndReturnAll();
//        } finally {
//            returnResource(jedis, broken);
//        }
//        List<Boolean> resultList = new ArrayList<Boolean>(plList.size());
//        for (Object object : plList) {
//            if (object == null) {
//                continue;
//            }
//            resultList.add((Long) object == 1l ? true : false);
//        }
//        return resultList;
//    }
//
//    @Override
//    public boolean exists(String key) {
//        boolean broken = false;
//        boolean result = false;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.exists(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public boolean expire(String key, int seconds) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.expire(SafeEncoder.encode(buildKeyByVersion(key)), seconds);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result == 1l ? true : false;
//    }
//
//    @Override
//    public List<String> keys(String like) {
//        boolean broken = false;
//        List<String> list = null;
//        Jedis jedis = getResource();
//        try {
//            Set<byte[]> set = jedis.keys(SafeEncoder.encode(buildKeyByVersion(like)));
//            Iterator<byte[]> iterator = set.iterator();
//            list = new ArrayList<String>();
//            while (iterator.hasNext()) {
//                byte[] key = iterator.next();
//                list.add(SafeEncoder.encode(key));
//            }
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return list;
//    }
//
//    @Override
//    public Long ttl(String key) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.ttl(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//
//    public Long llen(String key) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.llen(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public Long incr(String key) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.incr(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public Long decrBy(String key, long value) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.decrBy(SafeEncoder.encode(buildKeyByVersion(key)), value);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    /*------------------  List相关操作 相关操作方法---------------------*/
//    @Override
//    public <T> boolean lpush(String key, T value) {
//        byte[] valueBytes = SerializeUtils.serialize(value);
//        return lpush(key, valueBytes);
//    }
//
//    @Override
//    public boolean lpush(String key, byte[] value) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.lpush(SafeEncoder.encode(buildKeyByVersion(key)), value);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result == 1l ? true : false;
//    }
//
//    @Override
//    public <T> boolean rpush(String key, T value) {
//        byte[] valueBytes = SerializeUtils.serialize(value);
//        return rpush(key, valueBytes);
//    }
//
//    @Override
//    public boolean rpush(String key, byte[] value) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.rpush(SafeEncoder.encode(buildKeyByVersion(key)), value);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result == 1l ? true : false;
//    }
//
//    @Override
//    public <T> T lpop(String key) {
//        byte[] bytes = lpopBytes(key);
//        return (T) SerializeUtils.deserialize(bytes);
//    }
//
//    @Override
//    public byte[] lpopBytes(String key) {
//        boolean broken = false;
//        byte[] result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.lpop(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public <T> T rpop(String key) {
//        byte[] bytes = rpopBytes(key);
//        return (T) SerializeUtils.deserialize(bytes);
//    }
//
//    @Override
//    public byte[] rpopBytes(String key) {
//        boolean broken = false;
//        byte[] result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.rpop(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public <T> List<T> lrange(String key, int start, int end) {
//        boolean broken = false;
//        List<byte[]> list = null;
//        Jedis jedis = getResource();
//        try {
//            list = jedis.lrange(SafeEncoder.encode(buildKeyByVersion(key)), start, end);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        List<T> oList = new ArrayList<T>(list.size());
//        for (byte[] ba : list) {
//            oList.add((T) SerializeUtils.deserialize(ba));
//        }
//        return oList;
//    }
//
//	/*------------------ set 相关操作方法--------------------*/
//
//    public boolean saddBytes(String key, byte[] value) {
//        boolean broken = false;
//        Long result = 0L;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.sadd(SafeEncoder.encode(buildKeyByVersion(key)), value);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result == 1L ? true : false;
//    }
//
//    @Override
//    public <T> boolean sadd(String key, T value) {
//        byte[] valueBytes = SerializeUtils.serialize(value);
//        return saddBytes(key, valueBytes);
//    }
//
//    @Override
//    public Long scard(String key) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.scard(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//
//    protected byte[] spopBytes(String key) {
//        boolean broken = false;
//        byte[] result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.spop(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public <T> T spop(String key) {
//        byte[] bytes = spopBytes(key);
//        return (T) SerializeUtils.deserialize(bytes);
//    }
//
//    @Override
//    public <T> boolean sismember(String key, T member) {
//        boolean broken = false;
//        Jedis jedis = getResource();
//        try {
//            return jedis.sismember(SafeEncoder.encode(buildKeyByVersion(key)), SerializeUtils.serialize(member));
//        } finally {
//            returnResource(jedis, broken);
//        }
//    }
//
//    @Override
//    public <T> Set<T> smembers(String key) {
//        boolean broken = false;
//        Jedis jedis = getResource();
//        try {
//            Set<byte[]> set = jedis.smembers(SafeEncoder.encode(buildKeyByVersion(key)));
//            Set<T> returnSet = new HashSet<T>();
//            for (byte[] s : set) {
//                returnSet.add((T) SerializeUtils.deserialize(s));
//            }
//            return returnSet;
//        } finally {
//            returnResource(jedis, broken);
//        }
//    }
//
///*    @Override
//    public <T> boolean smove(String srckey, String dstkey, T member) {
//        return false;
//    }
//
//    @Override
//    public <T> boolean srem(String key, T... members) {
//        return false;
//    }
//
//    @Override
//    public <T> Set<T> sinter(String... keys) {
//        return null;
//    }
//
//    @Override
//    public <T> boolean sinterstore(String dstkey, T... keys) {
//        return false;
//    }
//
//    @Override
//    public <T> Set<T> sunion(String... keys) {
//        return null;
//    }
//
//    @Override
//    public <T> boolean sunionstore(String dstkey, T... keys) {
//        return false;
//    }
//
//    @Override
//    public <T> Set<T> sdiff(String... keys) {
//        return null;
//    }
//
//    @Override
//    public <T> boolean sdiffstore(String dstkey, T... keys) {
//        return false;
//    }
//
//    @Override
//    public <T> T srandmember(String key) {
//        return null;
//    }
//
//    @Override
//    public <T> List<T> srandmember(String key, int count) {
//        return null;
//    }*/
//
//	/*------------------ zset 相关操作方法--------------------*/
//
//  /*  @Override
//    public <T> boolean zadd(String key, double score, T member) {
//        return false;
//    }
//
//    @Override
//    public <T> boolean zadd(String key, Map<T, Double> scoreMembers) {
//        return false;
//    }
//
//    @Override
//    public <T> Set<T> zrange(String key, long start, long end) {
//        return null;
//    }
//
//    @Override
//    public <T> boolean zrem(String key, T... member) {
//        return false;
//    }
//
//    @Override
//    public <T> Double zincrby(String key, double score, T member) {
//        return null;
//    }
//
//    @Override
//    public <T> Long zrank(String key, T member) {
//        return null;
//    }
//
//    @Override
//    public <T> Long zrevrank(String key, T member) {
//        return null;
//    }
//
//    @Override
//    public <T> Set<T> zrevrange(String key, long start, long end) {
//        return null;
//    }
//
//    @Override
//    public Long zcard(String key) {
//        return null;
//    }
//
//    @Override
//    public <T> Double zscore(String key, T member) {
//        return null;
//    }*/
///*------------------ hash 相关操作方法---------------------*/
//
//    @Override
//    public <T> boolean hset(String key, String field, T value) {
//        byte[] valueBytes = SerializeUtils.serialize(value);
//        return hsetBytes(key, field, valueBytes);
//    }
//
//    @Override
//    public <T> T hget(String key, String field) {
//        byte[] bytes = hgetBytes(key, field);
//        return (T) SerializeUtils.deserialize(bytes);
//    }
//    
//	@Override
//	public Long hgetCount(String key,String field) {
//		 byte[] valueBytes = hgetBytes(key, field);
//		 if (valueBytes == null) {
//            return null;
//         }
//	     return Long.valueOf(SafeEncoder.encode(valueBytes));
//	}
//
//    @Override
//    public <T> List<T> hmget(String key, String... fields) {
//        List<byte[]> list = hmgetBytes(key, fields);
//        List<T> oList = new ArrayList<T>(list.size());
//        for (byte[] ba : list) {
//            oList.add((T) SerializeUtils.deserialize(ba));
//        }
//        return oList;
//    }
//
//    @Override
//    public <T> Map<String, T> hgetAll(String key) {
//        Map<byte[], byte[]> map = hgetAllBytes(key);
//        Map<String, T> omap = new HashMap<String, T>();
//        for (byte[] mapKey : map.keySet()) {
//            omap.put(SafeEncoder.encode(mapKey), (T) SerializeUtils.deserialize(map.get(mapKey)));
//        }
//        return omap;
//    }
//
//
//    @Override
//    public boolean hexists(String key, String field) {
//        boolean broken = false;
//        boolean result = false;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.hexists(buildKeyByVersion(key), field);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public Set<String> hkeys(String key) {
//        boolean broken = false;
//        Set<String> result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.hkeys(buildKeyByVersion(key));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    @Override
//    public boolean hdel(String key, String... fields) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.hdel(buildKeyByVersion(key), fields);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result == 1l ? true : false;
//    }
//    
//    @Override
//    public Long hincrBy(String key, String field,long value) {
//        boolean broken = false;
//        Long result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.hincrBy(SafeEncoder.encode(buildKeyByVersion(key)), SafeEncoder.encode(field), value);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    protected boolean hsetBytes(String key, String field, byte[] value) {
//        boolean broken = false;
//        Long result = 0L;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.hset(SafeEncoder.encode(buildKeyByVersion(key)), SafeEncoder.encode(field), value);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result == 1l ? true : false;
//    }
//
//    protected byte[] hgetBytes(String key, String field) {
//        boolean broken = false;
//        byte[] result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.hget(SafeEncoder.encode(buildKeyByVersion(key)), SafeEncoder.encode(field));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//    protected List<byte[]> hmgetBytes(String key, String... fields) {
//        boolean broken = false;
//        byte[][] byteFields = new byte[fields.length][];
//        for (int i = 0; i < fields.length; i++) {
//            byteFields[i] = SafeEncoder.encode(fields[i]);
//        }
//        List<byte[]> result = null;
//        Jedis jedis = getResource();
//        try {
//            result = jedis.hmget(SafeEncoder.encode(buildKeyByVersion(key)), byteFields);
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//
//    protected Map<byte[], byte[]> hgetAllBytes(String key) {
//        boolean broken = false;
//        Map<byte[], byte[]> result = null;
//        Jedis jedis = getResource();
//
//        try {
//            result = jedis.hgetAll(SafeEncoder.encode(buildKeyByVersion(key)));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result;
//    }
//
//
//    /*------------------ Pub/Sub 相关操作方法---------------------*/
//    @Override
//    public <T> boolean publish(String channel, T message) {
//        boolean broken = false;
//        Jedis jedis = getResource();
//        Long result = 0L;
//        try {
//            result = jedis.publish(SafeEncoder.encode(channel), SerializeUtils.serialize(message));
//        } finally {
//            returnResource(jedis, broken);
//        }
//        return result == 0L ? false : true;
//    }
//
//    @Override
//    public void psubscribe(JedisPubSub jedisPubSub, String[] patterns) {
//        boolean broken = false;
//        Jedis jedis = getResource();
//        try {
//            jedis.psubscribe(jedisPubSub, patterns);
//        } finally {
//            returnResource(jedis, broken);
//        }
//    }
//
//	/*------------------ clear cache---------------------*/
//
//    @Override
//    public void flushAll() {
//        Jedis jedis = getResource();
//        jedis.flushAll();
//    }
//}
