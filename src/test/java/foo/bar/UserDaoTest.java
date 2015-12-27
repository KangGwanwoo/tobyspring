package foo.bar;

import chap1.springbook.user.dao.ConnectionMaker;
import chap1.springbook.user.dao.DConnectionMaker;
import chap1.springbook.user.dao.DaoFactory;
import chap1.springbook.user.dao.UserDao;
import chap1.springbook.user.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by daum on 15. 12. 20..
 */
public class UserDaoTest {

    @Test
    public void addAndGet() throws ClassNotFoundException,SQLException{
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring-config.xml");
        UserDao dao = context.getBean("userDao",UserDao.class);
        User user = new User();
        user.setId("gyumee");
        user.setName("박상철");
        user.setPassword("springno1");

        dao.add(user);

        User user2 = dao.get(user.getId());

        Assert.assertThat(user2.getName(),is(user.getName()));
        Assert.assertThat(user2.getPassword(),is(user.getPassword()));

    }
}
