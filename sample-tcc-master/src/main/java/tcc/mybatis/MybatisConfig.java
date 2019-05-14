package tcc.mybatis;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@MapperScan(basePackages = {"tcc.dao"})
@EnableTransactionManagement
@EnableWebMvc
public class MybatisConfig {

	@Bean
    public static BasicDataSource basicDataSource(
//            @Value("${spring.datasource.driver-class-name}") String driver,
            @Value("${spring.datasource.postgre.url}") String url,
            @Value("${spring.datasource.postgre.username}") String userName,
            @Value("${spring.datasource.postgre.password}") String password
    ) {
        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean
    public static DataSourceTransactionManager dataSourceTransactionManager(BasicDataSource basicDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(basicDataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public static SqlSessionFactory sqlSessionFactoryBean (BasicDataSource basicDataSource) throws Exception{

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //sqlSessionFactoryBean.setTypeAliasesPackage("vo클래스 주입");
        sqlSessionFactoryBean.setDataSource(basicDataSource);
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources("classpath:/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public static SqlSessionTemplate sqlSessionTemplate (SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/test/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
