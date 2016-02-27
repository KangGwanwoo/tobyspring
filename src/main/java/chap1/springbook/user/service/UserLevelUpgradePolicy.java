package chap1.springbook.user.service;

import chap1.springbook.user.dao.UserDao;
import chap1.springbook.user.domain.Level;
import chap1.springbook.user.domain.User;

/**
 * Created by daum on 16. 1. 7..
 */
public interface UserLevelUpgradePolicy {


    boolean canUpgradeLevel(User user);

    void upgradeLevel(User user);
}
