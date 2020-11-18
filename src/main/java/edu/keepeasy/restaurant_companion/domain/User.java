package edu.keepeasy.restaurant_companion.domain;

public class User extends Entity {
    private String name;
    private String login;
    private String password;
    private UserType userType;

    public User(long id, String name, String login, String password, UserType userType) {
        super(id);
        this.name = name;
        this.login = login;
        this.password = password;
        this.userType = userType;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public enum UserType {
        UNDEFINED(0),
        ADMIN(1),
        COOK(2),
        WAITER(3);
        private final int value;

        UserType(int value) {
            this.value = value;
        }

        public static UserType fromInteger(int value) {
            return switch (value) {
                case 1 -> ADMIN;
                case 2 -> COOK;
                case 3 -> WAITER;
                default -> UNDEFINED;
            };
        }

        public int getValue() {
            return value;
        }
    }
}
