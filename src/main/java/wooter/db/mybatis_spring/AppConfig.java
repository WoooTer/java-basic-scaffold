package wooter.db.mybatis_spring;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import wooter.db.balusc.dao.DAOProperties;
import wooter.db.mybatis.mapper.ProductMapper;

import javax.sql.DataSource;

/**
 * [How to use the Spring FactoryBean?](https://www.baeldung.com/spring-factorybean)
 */
@Configuration
//@MapperScan(basePackageClasses = ProductMapper.class)
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DAOProperties properties = new DAOProperties();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(properties.getUrl());
        dataSource.setUser(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations();
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 若不使用 @MapperScan 则需要手动注册 Mapper。
     *
     * MapperFactoryBean 将会负责 SqlSession 的创建和关闭。
     * 如果使用了 Spring 的事务功能，那么当事务完成时，session 将会被提交或回滚。
     * 最终任何异常都会被转换成 Spring 的 DataAccessException 异常。
     */
    @Bean
    public MapperFactoryBean MapperFactoryBean() throws Exception{
        MapperFactoryBean<ProductMapper> factoryBean = new MapperFactoryBean<>();
        factoryBean.setSqlSessionFactory(sqlSessionFactory());
        factoryBean.setMapperInterface(ProductMapper.class);
        return factoryBean;
    }

    /**
     * SqlSessionTemplate 是 MyBatis-Spring 的核心。
     * 作为 SqlSession 的一个实现，这意味着可以使用它无缝代替你代码中已经在使用的 SqlSession。
     * SqlSessionTemplate 是线程安全的，可以被多个 DAO 或映射器所共享使用。
     */
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductMapper productMapper = ctx.getBean(ProductMapper.class);
        productMapper.selectList();
    }
}
