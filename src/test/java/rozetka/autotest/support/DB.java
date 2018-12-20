package rozetka.autotest.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DB {

    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/JavaDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            //jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e){System.out.println(e);}


        return null;
    }

    public static void post(String[][] products) throws Exception{
        Connection con = getConnection();
        PreparedStatement posted = null;

        try{
            for (String[] product : products) {
                if (con != null) {
                    posted = con.prepareStatement("INSERT INTO products (name, price) VALUES ('" + product[0] + "', '" + product[1] + "')");
                }

                if (posted != null) {
                    posted.executeUpdate();
                }
            }

        } catch(Exception e){System.out.println(e);}
        finally {
            System.out.println("Insert Completed.");
        }
    }
}
