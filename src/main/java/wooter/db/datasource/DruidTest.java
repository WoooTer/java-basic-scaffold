package wooter.db.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import wooter.db.balusc.dao.DAOProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DruidTest {

    public static void main(String[] args) {
        DAOProperties properties = new DAOProperties();

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "select * from product";
                try (ResultSet result = statement.executeQuery(sql)) {
                    while (result.next()) {
                        System.out.println(result.getInt("id") + "  " + result.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
