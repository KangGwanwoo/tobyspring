package chap1.springbook.user.service;

import chap1.springbook.user.dao.UserDao;
import chap1.springbook.user.domain.Level;
import chap1.springbook.user.domain.User;
import org.junit.Test;

import java.util.List;

/**
 * Created by daum on 16. 1. 6..
 */
public class UserService {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevels(){
        List<User> users = userDao.getAll();

        for(User user : users){
            if(canupgradeLevel(user)){
                upgradeLevel(user);
            }

        }
    }

    private void upgradeLevel(User user){
        user.upgradeLevel();
        userDao.update(user);
    }
    private boolean canupgradeLevel(User user){
        Level currentLevel = user.getLevel();
        switch (currentLevel){
            case BASIC:return (user.getLogin() >= 50);
            case SILVER:return (user.getRecommend() >= 30);
            case GOLD:return false;
            default:throw new IllegalArgumentException("Unknown Level :"+currentLevel);
        }
    }
    public void add(User user) {
        if (user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }
}
