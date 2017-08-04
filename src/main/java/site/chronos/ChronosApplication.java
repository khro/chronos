package site.chronos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("site.chronos.mapper")
public class ChronosApplication {
	
//	@Autowired
//	private Environment env;
	
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
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();        
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringSerializer );
//        template.setValueSerializer(stringSerializer );
//        template.setHashKeySerializer(stringSerializer );
//        template.setHashValueSerializer(stringSerializer );
        return template;
    }


//	@Bean(destroyMethod = "shutdown")
//	public RedissonClient redissonClient() throws IOException {
//		String[] profiles = env.getActiveProfiles();
//		String profile = "local";
//		if(profiles.length > 0) {
//			profile = "-" + profiles[0];
//		}
////		io.netty.util.NettyRuntime
//		InputStream inputStream = new ClassPathResource("redisson-" + profile + ".yml").getInputStream();
//		System.out.println("*******"+inputStream);
//		return Redisson.create(
//				Config.fromYAML(inputStream)
//		);
//	}
}
