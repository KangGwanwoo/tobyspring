package chap1.springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by daum on 15. 12. 20..
 */

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao(){
        UserDao dao = new UserDao();
        dao.setDataSource(dataSource());
        return dao;
    }


    @Bean
    public DataSource dataSource(){
        return new SimpleDriverDataSource();
    }
}
