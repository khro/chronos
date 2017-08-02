package site.chronos.utils.cache;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * redissession 统一session管理
 * @author xw
 *
 */

@EnableRedisHttpSession
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfiguration {

	@Resource
	RedisProperties redisProperties;
	@Bean
	public LettuceConnectionFactory connectionFactory(){
		LettuceConnectionFactory factory = new LettuceConnectionFactory();
		System.out.println(redisProperties.toString());
		factory.setHostName(redisProperties.getHost());
		factory.setDatabase(redisProperties.getDatabase());
		factory.setPassword(redisProperties.getPassword());
		if(redisProperties.getTimeout() > 0){
			factory.setTimeout(redisProperties.getTimeout());
		}
		factory.setPort(redisProperties.getPort());
		//RedisTemplate
		return factory;
	}
}
