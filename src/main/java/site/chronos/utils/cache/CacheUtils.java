package site.chronos.utils.cache;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;
import site.chronos.utils.cache.service.CacheClient;

@Service
public class CacheUtils implements CacheClient {

	@Autowired
	private CacheClient cacheClient;

	public String getKeyVersion() {
		return cacheClient.getKeyVersion();
	}

	public <T> boolean set(String key, T value) {
		try {
			return cacheClient.set(key, value);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public <T> boolean set(String key, int expiredTime, T value) {
		try {
			return cacheClient.set(key, expiredTime, value);
		} catch (JedisConnectionException e) {
			return false;
		}

	}

	public <T> T get(String key) {
		try {
			return cacheClient.get(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	/**
	 * ，对自增、自减的key获取其计数值
	 */
	public Long getCount(String key) {
		try {
			return cacheClient.getCount(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public <T> List<T> batchGet(String... keys) {
		try {
			if (keys == null || keys.length == 0) {
				return null;
			}
			return cacheClient.batchGet(keys);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public <T> List<T> batchGet(List<String> keyList) {
		try {
			if (keyList.isEmpty()) {
				return null;
			}
			String[] keysString = new String[keyList.size()];
			keyList.toArray(keysString);
			return batchGet(keysString);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public boolean delete(String key) {
		try {
			return cacheClient.delete(key);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public List<Boolean> batchDelete(String... keys) {
		try {
			if (keys == null || keys.length == 0) {
				return null;
			}
			return cacheClient.batchDelete(keys);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public List<Boolean> batchDelete(List<String> keyList) {
		try {
			if (keyList == null || keyList.isEmpty()) {
				return null;
			}

			String[] keysString = new String[keyList.size()];
			keyList.toArray(keysString);
			return batchDelete(keysString);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public boolean exists(String key) {
		try {
			return cacheClient.exists(key);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public boolean expire(String key, int expire) {
		try {
			return cacheClient.expire(key, expire);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public List<String> keys(String like) {
		try {
			return cacheClient.keys(like);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public Long ttl(String key) {
		try {
			return cacheClient.ttl(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public Long llen(String key) {
		try {
			return cacheClient.llen(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public Long decrBy(String key, long integer) {
		try {
			return cacheClient.decrBy(key, integer);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public Long incr(String key) {
		try {
			return cacheClient.incr(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public <T> boolean lpush(String key, T value) {
		try {
			return cacheClient.lpush(key, value);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public <T> boolean rpush(String key, T value) {
		try {
			return cacheClient.rpush(key, value);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public <T> T lpop(String key) {
		try {
			return cacheClient.lpop(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public <T> T rpop(String key) {
		try {
			return cacheClient.rpop(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public <T> List<T> lrange(String key, int start, int end) {
		try {
			return cacheClient.lrange(key, start, end);
		} catch (JedisConnectionException e) {
			return null;
		}
	}
	/*------------------ set 相关操作方法 start--------------------*/

	public <T> boolean sadd(String key, T value) {
		try {
			return cacheClient.sadd(key, value);
		} catch (Exception e) {
			return false;
		}
	}

	public Long scard(String key) {
		try {
			return cacheClient.scard(key);
		} catch (Exception e) {
			return -1L;
		}
	}

	public <T> T spop(String key) {
		try {
			return cacheClient.spop(key);
		} catch (Exception e) {
			return null;
		}
	}

	public <T> Set<T> smembers(String key) {
		try {
			return cacheClient.smembers(key);
		} catch (Exception e) {
			return Collections.emptySet();
		}
	}

	public <T> boolean sismember(String key, T member) {
		try {
			return cacheClient.sismember(key, member);
		} catch (Exception e) {
			return false;
		}
	}

	/*------------------ set 相关操作方法 end--------------------*/
	public <T> boolean hset(String key, String field, T value) {
		try {
			return cacheClient.hset(key, field, value);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public <T> T hget(String key, String field) {
		try {
			return cacheClient.hget(key, field);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	/**
	 * ，对自增、自减的key获取其计数值
	 */
	public Long hgetCount(String key, String field) {
		try {
			return cacheClient.hgetCount(key, field);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public <T> List<T> hmget(String key, String... fields) {
		return cacheClient.hmget(key, fields);
	}

	public boolean hexists(String key, String field) {
		try {
			return cacheClient.hexists(key, field);
		} catch (Exception e) {
			return false;
		}
	}

	public Set<String> hkeys(String key) {
		try {
			return cacheClient.hkeys(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public <T> Map<String, T> hgetAll(String key) {
		try {
			return cacheClient.hgetAll(key);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public boolean hdel(String key, String... fields) {
		try {
			return cacheClient.hdel(key, fields);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public Long hincrBy(String key, String field, long value) {
		try {
			return cacheClient.hincrBy(key, field, value);
		} catch (JedisConnectionException e) {
			return null;
		}
	}

	public <T> boolean publish(final String channel, T message) {
		try {
			return cacheClient.publish(channel, message);
		} catch (JedisConnectionException e) {
			return false;
		}
	}

	public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
		cacheClient.psubscribe(jedisPubSub, patterns);
	}

	public void flushAll() {
		cacheClient.flushAll();
	}

	@Override
	public boolean set(String key, byte[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean set(String key, int seconds, byte[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] getBytes(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean lpush(String key, byte[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rpush(String key, byte[] value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] lpopBytes(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] rpopBytes(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
