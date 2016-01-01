package chap1.springbook.user.dao;

import javax.sql.DataSource;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by daum on 16. 1. 1..
 */
public class JdbcContext {
    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void executeSql(final String query) throws SQLException{
        workStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                return c.prepareStatement(query);
            }
        });
    }
    public void workStatementStrategy(StatementStrategy stmt) throws SQLException{

        Connection c = null;
        PreparedStatement ps = null;

        try{
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        }catch (SQLException se){
            throw se;
        }finally {
            if(ps != null) {
                try {
                    ps.close();
                }catch (SQLException e){
                }
            }
            if(c != null){
                try{
                    c.close();
                }catch (SQLException e){

                }
            }
        }
    }
}
