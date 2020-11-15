package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.User;
import edu.keepeasy.restaurant_companion.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;

@org.springframework.stereotype.Repository
public class UserRepo implements Repository<User> {
    private static final String insertQuery = "INSERT INTO user(name, login, passwordHash, userType) VALUE (?,?,?,?)";

    @Autowired
    JdbcOperations jdbcOperations;

    @Override
    public User create(User entity) {
        Object[] args = new Object[]{entity.getName(), entity.getLogin(), entity.getPassword(), entity.getUserType().getValue()};
        int[] argTypes = new int[]{Types.CHAR, Types.CHAR, Types.CHAR, Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery);
        return getResult(rowSet);
    }

    @Override
    public User[] readAll() {
        return new User[0];
    }

    @Override
    public User read(long id) {
        return null;
    }

    @Override
    public User update(long id, User entity) {
        return null;
    }

    @Override
    public User delete(User entity) {
        return null;
    }

    private User getResult(SqlRowSet rowSet) {
        if (rowSet.next()) {
            return new User(
                    rowSet.getLong("id"),
                    rowSet.getString("name"),
                    rowSet.getString("login"),
                    rowSet.getString("password"),
                    UserType.fromInteger(rowSet.getInt("userType")));
        } else {
            return null;
        }
    }
}
