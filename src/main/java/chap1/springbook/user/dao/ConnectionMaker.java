package chap1.springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by daum on 15. 12. 20..
 */
public interface ConnectionMaker {
    Connection makeConnection() throws ClassNotFoundException, SQLException;
}
