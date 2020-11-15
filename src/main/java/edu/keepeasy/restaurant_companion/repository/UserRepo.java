package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.ArrayList;

@org.springframework.stereotype.Repository
public class UserRepo implements Repository<User> {
    private static final String insertQuery = "INSERT INTO user(name, login, passwordHash, userType) VALUE (?,?,?,?)";
    private static final String selectAllQuery = "SELECT id, name, login, passwordHash, userType FROM user";
    private static final String selectByID = "SELECT id, name, login, passwordHash, userType FROM user where id=?";
    private static final String deleteQuery = "DELETE FROM user where id=?";
    private static final String updateQuery = "UPDATE user SET id=?,name=?,login=?,passwordHash=?,userType=? where id=?";
    @Autowired
    JdbcOperations jdbcOperations;

    @Override
    public User create(User entity) {
        Object[] args = new Object[]{
                entity.getName(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getUserType().getValue()};
        int[] argTypes = new int[]{
                Types.CHAR,
                Types.CHAR,
                Types.CHAR,
                Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, args, argTypes);
        return getResult(rowSet);
    }

    @Override
    public User[] readAll() {
        ArrayList<User> users = new ArrayList<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectAllQuery);
        User user = getResult(rowSet);
        while (user != null) {
            users.add(user);
            user = getResult(rowSet);
        }
        return users.toArray(new User[0]);
    }

    @Override
    public User read(long id) {
        Object[] args = new Object[]{id};
        int[] argTypes = new int[]{Types.BIGINT};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByID, args, argTypes);
        return getResult(rowSet);
    }

    @Override
    public User update(long id, User entity) {
        Object[] args = new Object[]{
                entity.getId(),
                entity.getName(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getUserType().getValue(),
                id};
        int[] argTypes = new int[]{
                Types.BIGINT,
                Types.CHAR,
                Types.CHAR,
                Types.CHAR,
                Types.INTEGER,
                Types.BIGINT};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, args, argTypes);
        return getResult(rowSet);
    }

    @Override
    public User delete(User entity) {
        Object[] args = new Object[]{
                entity.getId()};
        int[] argTypes = new int[]{
                Types.BIGINT};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery);
        return getResult(rowSet);
    }

    private User getResult(SqlRowSet rowSet) {
        if (rowSet.next()) {
            return new User(
                    rowSet.getLong("id"),
                    rowSet.getString("name"),
                    rowSet.getString("login"),
                    rowSet.getString("password"),
                    User.UserType.fromInteger(rowSet.getInt("userType")));
        } else {
            return null;
        }
    }
}
