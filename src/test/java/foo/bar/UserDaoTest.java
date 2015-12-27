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
    public void addAndGet() throws ClassNotFoundException, SQLException {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring-config.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);
        User user1 = new User("gyumee", "박상철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");

        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));


        dao.add(user1);
        dao.add(user2);
        Assert.assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        Assert.assertThat(userget1.getName(), is(user1.getName()));
        Assert.assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        Assert.assertThat(userget2.getName(), is(user2.getName()));
        Assert.assertThat(userget2.getPassword(), is(user2.getPassword()));
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring-config.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user = new User("gyumee", "박상철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");
        User user3 = new User("bumjin", "박범진", "springno3");


        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));
        dao.add(user);
        Assert.assertThat(dao.getCount(), is(1));
        dao.add(user2);
        Assert.assertThat(dao.getCount(), is(2));
        dao.add(user3);
        Assert.assertThat(dao.getCount(), is(3));

    }
}
