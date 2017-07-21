package site.chronos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import site.chronos.utils.cache.JedisClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@MapperScan("site.chronos.mapper")
public class ChronosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChronosApplication.class, args);
	}
	
	/**
	 * bcrypt加密
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bcrypt(){
		return new BCryptPasswordEncoder();
	} 
	
	@Bean
	public JedisPoolConfig getJedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(100);
		jedisPoolConfig.setMaxIdle(10);
		jedisPoolConfig.setMinIdle(2);
		jedisPoolConfig.setMaxWaitMillis(5000);
		return jedisPoolConfig;
	}
	@Bean
	public JedisPool getJedisPool(){
		JedisPool jedisPool = new JedisPool(getJedisPoolConfig(), "localhost", 6379, 5000, "redisxiao", 0);
		return jedisPool;
	}
	@Bean
	public JedisClient getJedisClient(){
		JedisClient jedisClient = new JedisClient();
		jedisClient.setJedisPool(getJedisPool());
		return jedisClient;
	}
}
