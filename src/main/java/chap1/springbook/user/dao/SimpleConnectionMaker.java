package chap1.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by daum on 15. 12. 20..
 */
public class SimpleConnectionMaker {
    public Connection makeNewConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/tobySpring", "root", "Starter?5"
        );
        return c;
    }
}
