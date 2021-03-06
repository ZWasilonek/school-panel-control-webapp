package pl.codeschool.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String userName;
    private String email;
    private String password;
    private Group group;
    private boolean isAdmin;

    public User() {}

    public User(String userName, String email, String password, Group group) {
        this.userName = userName;
        this.email = email;
        this.hashPassword(password);
        this.group = group;
        this.isAdmin = false;
    }

    public User(int id, String userName, String email, String password, Group group, boolean isAdmin) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.hashPassword(password);
        this.group = group;
        this.isAdmin = isAdmin;
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}