package chap1.springbook.user;

import chap1.springbook.user.dao.UserDao;
import chap1.springbook.user.dao.UserDaoJdbc;
import chap1.springbook.user.domain.Level;
import chap1.springbook.user.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by daum on 15. 12. 20..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config.xml")
public class UserDaoTest {
    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserDao dao;

    @Before
    public void setUp() {

        dao = context.getBean("userDao", UserDao.class);
        user1 = new User("gyumee", "박상철", "springno1", Level.BASIC, 1, 0);
        user2 = new User("leegw700", "이길원", "springno2", Level.SILVER, 55, 10);
        user3 = new User("bumjin", "박범진", "springno3", Level.GOLD, 100, 40);
    }

    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {

        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));


        dao.add(user1);
        dao.add(user2);
        Assert.assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = dao.get(user2.getId());
        checkSameUser(userget2, user2);
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
    public void getUserFaulure() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        Assert.assertThat(dao.getCount(), is(0));

        dao.get("unkown_id");
    }


    @Test
    public void getAll() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        dao.add(user1);
        List<User> users1 = dao.getAll();
        checkSameUser(user1, users1.get(0));
    }

    public void checkSameUser(User user1, User user2) {

        Assert.assertThat(user1.getId(), is(user2.getId()));
        Assert.assertThat(user1.getLevel(), is(user2.getLevel()));
        Assert.assertThat(user1.getLogin(), is(user2.getLogin()));
    }

    @Test(expected = DataAccessException.class)
    public void duplciateKey() {
        dao.deleteAll();
        dao.add(user1);
        dao.add(user1);
    }

    @Test
    public void update() {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2)

        ;
        user1.setName("오민규");
        user1.setPassword("spring06");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        Assert.assertEquals(dao.update(user1), 1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1, user1update);

        User user2same = dao.get(user2.getId());
        checkSameUser(user2, user2same);
    }
}
