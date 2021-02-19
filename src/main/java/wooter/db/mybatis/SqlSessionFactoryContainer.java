package wooter.db.mybatis;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import wooter.db.balusc.dao.DAOProperties;
import wooter.db.mybatis.mapper.ProductMapper;

import javax.sql.DataSource;

public class SqlSessionFactoryContainer {

    private volatile static SqlSessionFactory singleton;

    private SqlSessionFactoryContainer() {
    }

    public static SqlSessionFactory getFactory() {
        if (singleton == null) {
            synchronized (SqlSessionFactoryContainer.class) {
                if (singleton == null) {
                    singleton = createSqlSessionFactory();
                }
            }
        }
        return singleton;
    }

    private static SqlSessionFactory createSqlSessionFactory() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, getDataSource());

        Configuration configuration = new Configuration(environment);
        addMappers(configuration);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    private static DataSource getDataSource(){
        DAOProperties properties = new DAOProperties();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(properties.getUrl());
        dataSource.setUser(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    private static void addMappers(Configuration configuration){
        configuration.addMapper(ProductMapper.class);
    }

}
