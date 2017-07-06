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
//	@Autowired
//	private DataSource dataSource;
//
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return new DataSourceTransactionManager(dataSource);
//	}

//	@Bean(name = "sqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactoryBean(PageHelper pageHelper) {
//		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setDataSource(dataSource);
//
//		// 自定义数据库配置的时候，需要将pageHelper的bean注入到Plugins中，如果采用系统默认的数据库配置，则只需要定义pageHelper的bean，会自动注入。
//		bean.setPlugins(new Interceptor[] { pageHelper });
//		try {
//			return bean.getObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
	
//	   @Bean
//	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//	        return new SqlSessionTemplate(sqlSessionFactory);
//	    }
	    
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
	    /**
		 * 分页插件
		 * @return
		 */
//		 @Bean  
//	     public PageHelper pageHelper() {  
//	        System.out.println("MyBatisConfiguration.pageHelper()");  
//	         PageHelper pageHelper = new PageHelper();  
//	         Properties p = new Properties();  
//	         p.setProperty("offsetAsPageNum", "true");  
//	         p.setProperty("rowBoundsWithCount", "true");  
//	         p.setProperty("reasonable", "true");  
//	         p.setProperty("supportMethodsArguments", "false");
//	         p.setProperty("pageSizeZero", "true");
//	         pageHelper.setProperties(p);  
//	         return pageHelper;  
//	     }  
}