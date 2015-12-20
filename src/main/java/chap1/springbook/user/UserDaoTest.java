package chap1.springbook.user;

import chap1.springbook.user.dao.ConnectionMaker;
import chap1.springbook.user.dao.DConnectionMaker;
import chap1.springbook.user.dao.DaoFactory;
import chap1.springbook.user.dao.UserDao;
import chap1.springbook.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

/**
 * Created by daum on 15. 12. 20..
 */
public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException,SQLException{
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring-config.xml");
        UserDao dao = context.getBean("userDao",UserDao.class);
        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());

        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회 성공");
    }
}
