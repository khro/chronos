package site.chronos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
	
	
}
