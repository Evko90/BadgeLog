package Server.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BadgeDataBase {
   String url = "jdbc:mysql://localhost:3306/BadgeLog?characterEncoding=UTF-8";
   String username = "phpmyadmin";
   String password = "Evko";

    private static BadgeDataBase instance;

    public static BadgeDataBase getInstance() throws IllegalAccessException, InstantiationException,
            ClassNotFoundException {
        synchronized(BadgeDataBase.class) {
            if (instance == null) {
                instance = new BadgeDataBase();
            }
        }
        return instance;
    }

   private BadgeDataBase() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
           Class.forName("com.mysql.jdbc.Driver").newInstance();

   }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
