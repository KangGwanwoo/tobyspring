package chap1.springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by daum on 15. 12. 20..
 */

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao(){
        UserDao dao = new UserDao();
        dao.setConnectionMaker(connectionMaker());
        return dao;
    }


    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
