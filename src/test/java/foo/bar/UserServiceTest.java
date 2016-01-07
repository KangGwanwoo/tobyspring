package foo.bar;

import chap1.springbook.user.dao.UserDao;
import chap1.springbook.user.domain.Level;
import chap1.springbook.user.domain.User;
import chap1.springbook.user.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static chap1.springbook.user.service.UserService.MIN_RECCOMEND_FOR_GOLD;
import static chap1.springbook.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
/**
 * Created by daum on 16. 1. 6..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/spring-config.xml")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    List<User> users;

    @Before
    public void setUp(){

        users = Arrays.asList(
                new User("bumjin","박범진","p1", Level.BASIC,MIN_LOGCOUNT_FOR_SILVER-1,0),
                new User("joytouch","강명성","p2", Level.BASIC,MIN_LOGCOUNT_FOR_SILVER,0),
                new User("erwins","신승환","p3", Level.SILVER,60,MIN_RECCOMEND_FOR_GOLD-1),
                new User("madnite1","이상호","p4", Level.SILVER,60,MIN_RECCOMEND_FOR_GOLD),
                new User("green","오민규","p5", Level.GOLD,100,100)
        );
    }

    @Test
    public void bean(){
        Assert.assertThat(this.userService, is(notNullValue()));
    }

    @Test
    public void upgradeLevelsTest(){
        userDao.deleteAll();
        for(User user:users) userDao.add(user);

        userService.upgradeLevels();
        checkLevel(users.get(0), false);
        checkLevel(users.get(1), true);
        checkLevel(users.get(2), false);
        checkLevel(users.get(3), true);
        checkLevel(users.get(4), false);



    }

    @Test
    public void add(){
        userDao.deleteAll();

        User userWithLevel = users.get(4); // GOLD 레벨
        User userWithoutLevel = users.get(0);
        userWithoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        Assert.assertThat(userWithLevelRead.getLevel(),is(userWithLevel.getLevel()));
        Assert.assertThat(userWithoutLevelRead.getLevel(), is(userWithoutLevel.getLevel()));

    }
    private void checkLevel(User user, boolean upgraded) {

        User userUpdate = userDao.get(user.getId());
        if(upgraded){
            Assert.assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
        }else{
            Assert.assertThat(userUpdate.getLevel(), is(user.getLevel()));
        }
    }

}
