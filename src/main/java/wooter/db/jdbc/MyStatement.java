package wooter.db.jdbc;

import wooter.db.balusc.dao.DAOProperties;

import java.sql.*;

/**
 * [Jenkov - Java JDBC](http://tutorials.jenkov.com/jdbc/index.html)
 * [The Java™ Tutorials - JDBC Database Access](https://docs.oracle.com/javase/tutorial/jdbc/)
 */
public class MyStatement {

    public static void main(String[] args) throws Exception {
        DAOProperties properties = new DAOProperties();
        String url = properties.getUrl();
        String user = properties.getUsername();
        String password = properties.getPassword();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
