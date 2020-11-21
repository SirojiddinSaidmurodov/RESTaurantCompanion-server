package edu.keepeasy.restaurant_companion.resource;

import edu.keepeasy.restaurant_companion.domain.User;

public class UserResource extends Resource {
    private long id;
    private String name;
    private String login;
    private String password;
    private int userType;

    public UserResource() {

    }

    public UserResource(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.userType = user.getUserType().getValue();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public User toEntity() {
        return new User(
                id,
                name,
                login,
                password,
                User.UserType.fromInteger(userType));
    }
}
