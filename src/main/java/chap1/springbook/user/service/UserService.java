package chap1.springbook.user.service;

import chap1.springbook.user.dao.UserDao;
import chap1.springbook.user.domain.Level;
import chap1.springbook.user.domain.User;
import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by daum on 16. 1. 6..
 */
public class UserService {


    UserDao userDao;

    private DataSource dataSource;


    public UserLevelUpgradePolicy userLevelUpgradePolicy;

    public void setUserLevelUpgradePolicy(UserLevelUpgradePolicy userLevelUpgradePolicy) {
        this.userLevelUpgradePolicy = userLevelUpgradePolicy;
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevels() throws Exception {
        TransactionSynchronizationManager.initSynchronization(); // 트랜잭션 동기화 관리자를 이용해 동기화 작업을 초기화한다.
        Connection c = DataSourceUtils.getConnection(dataSource); // DB커넥션을 생성하고 트랜잭션을 시작한다.
        c.setAutoCommit(false);
        try {
            List<User> users = userDao.getAll();

            for (User user : users) {
                if (userLevelUpgradePolicy.canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }

            }

            c.commit(); // 정상적으로 작업을 마치면 트랜잭션 커밋
        }catch (Exception e){
            c.rollback(); //예외가 발생하면 롤백한다.
            throw e;
        }finally {
            DataSourceUtils.releaseConnection(c,dataSource); // 스프링 유틸리티 메소드를 이용해 DB 커넥션을 안전하게 닫는다.
            TransactionSynchronizationManager.unbindResource(this.dataSource);
            TransactionSynchronizationManager.clearSynchronization(); // 동기화 작업 종료 및 정리
        }
    }

    protected void upgradeLevel(User user){
        user.upgradeLevel();
        userDao.update(user);
    }

    public void add(User user) {
        if (user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
