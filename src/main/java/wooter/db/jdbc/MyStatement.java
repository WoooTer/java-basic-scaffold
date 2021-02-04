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

        updateManualCommit(url, user, password);
    }

    public static void select(String url, String user, String password) {
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

    public static void updateAutoCommit(String url, String user, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "update product set name = 'jdbcAuto' where id = 6";
                int count = statement.executeUpdate(sql);
                System.out.println(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateManualCommit(String url, String user, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                String sql = "update product set name = 'jdbcAuto' where id = 6";
                int count = statement.executeUpdate(sql);
                System.out.println(count);

                connection.commit();
            } catch (Exception ex) {
                ex.printStackTrace();

                try {
                    if (connection != null) connection.rollback();
                } catch (SQLException excep) {
                    excep.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
