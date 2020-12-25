package wooter.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String args[]) {
        try {

//            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.153:3306/wtdb", "wooter", "Wt123123123.");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from product");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2));

//            stmt.executeUpdate("INSERT INTO product (name) VALUES ( '毛衣')");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

