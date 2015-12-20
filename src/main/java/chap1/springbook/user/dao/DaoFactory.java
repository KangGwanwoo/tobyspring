package chap1.springbook.user.dao;

/**
 * Created by daum on 15. 12. 20..
 */
public class DaoFactory {
    public UserDao userDao(){

        ConnectionMaker connectionMaker = new DConnectionMaker();

        UserDao dao = new UserDao(connectionMaker);

        return dao;
    }
}
