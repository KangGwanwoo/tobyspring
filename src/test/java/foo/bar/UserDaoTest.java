package foo.bar;

import chap1.springbook.user.dao.ConnectionMaker;
import chap1.springbook.user.dao.DConnectionMaker;
import chap1.springbook.user.dao.DaoFactory;
import chap1.springbook.user.dao.UserDao;
import chap1.springbook.user.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by daum on 15. 12. 20..
 */
public class UserDaoTest {
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp(){
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("spring-config.xml");

        dao = context.getBean("userDao", UserDao.class);

        user1 = new User("gyumee", "박상철", "springno1");
        user2 = new User("leegw700", "이길원", "springno2");
        user3 = new User("bumjin", "박범진", "springno3");
    }

    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {

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


        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));
        dao.add(user1);
        Assert.assertThat(dao.getCount(), is(1));
        dao.add(user2);
        Assert.assertThat(dao.getCount(), is(2));
        dao.add(user3);
        Assert.assertThat(dao.getCount(), is(3));

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getuserFaulure() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));

        dao.get("unkown_id");
    }
}
