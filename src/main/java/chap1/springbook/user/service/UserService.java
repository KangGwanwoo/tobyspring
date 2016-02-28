package chap1.springbook.user.service;

import chap1.springbook.user.domain.User;

/**
 * Created by kd4 on 2016. 2. 28..
 */
public interface UserService {
    void add(User user);
    void upgradeLevels();
}
