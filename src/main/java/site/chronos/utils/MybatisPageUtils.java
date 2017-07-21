package site.chronos.utils;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageHelper;

@Configuration
//加上这个注解，使得支持事务
@EnableTransactionManagement
public class MybatisPageUtils{
	    
	    @Bean
	    public PageHelper pageHelper() {
	        PageHelper pageHelper = new PageHelper();
	        Properties p = new Properties();
	        p.setProperty("offsetAsPageNum", "true");
	        p.setProperty("rowBoundsWithCount", "true");
	        p.setProperty("reasonable", "true");
	        p.setProperty("dialect", "mysql");
	        p.setProperty("supportMethodsArguments", "false");
	         p.setProperty("pageSizeZero", "true");
	        pageHelper.setProperties(p);
	        return pageHelper;
	    }
}