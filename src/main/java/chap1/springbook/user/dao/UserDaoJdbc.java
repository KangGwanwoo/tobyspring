package chap1.springbook.user.dao;

import chap1.springbook.user.domain.Level;
import chap1.springbook.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by daum on 15. 12. 19..
 */
public class UserDaoJdbc implements UserDao{
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper =
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user = new User();
                    user.setId(resultSet.getString("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setLevel(Level.valueOf(resultSet.getInt("level")));
                    user.setLogin(resultSet.getInt("login"));
                    user.setRecommend(resultSet.getInt("recommend"));
                    return user;
                }
            };


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserDaoJdbc(){
    }
    public void add(final User user) {
        this.jdbcTemplate.update("insert into users(id,name,password,level,login,recommend) values(?,?,?,?,?,?)", user.getId(),user.getName(),user.getPassword(),user.getLevel().intValue(),user.getLogin(),user.getRecommend());
    }

    public User get(String id){

        return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, userMapper);
    }

    public void deleteAll(){
        this.jdbcTemplate.update(
                "delete from users"
        );
    }


    public int getCount(){
        return this.jdbcTemplate.queryForInt("select count(*) from users");
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id",userMapper);
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update("update users set name =?, password = ?, level = ?, login = ?, recommend = ? where id = ? ",
                user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());
    }
}
