package chap1.springbook.user.domain;

/**
 * Created by daum on 15. 12. 19..
 */
public class User {
    String id;

    String name;

    String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
