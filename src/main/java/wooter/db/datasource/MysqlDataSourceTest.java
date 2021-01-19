package wooter.db.datasource;

import com.mysql.cj.jdbc.MysqlDataSource;
import wooter.db.balusc.dao.DAOProperties;

import javax.sql.DataSource;
import java.sql.*;

public class MysqlDataSourceTest {

    public static void main(String[] args) {
        DAOProperties properties = new DAOProperties();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(properties.getUrl());
        dataSource.setUser(properties.getUsername());
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
