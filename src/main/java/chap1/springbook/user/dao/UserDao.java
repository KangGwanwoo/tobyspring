package chap1.springbook.user.dao;

import chap1.springbook.user.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by daum on 16. 1. 3..
 */
public interface UserDao {
    void add(final User user);

    User get(String id);

    void deleteAll();

    int getCount();

    List<User> getAll();

    void update(User user1);
}
