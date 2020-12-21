package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@org.springframework.stereotype.Repository
public class UserRepo implements Repository<User> {
    private static final String selectAllQuery = "SELECT id, name, login, password, userType FROM user";
    private static final String selectByID = "SELECT id, name, login, password, userType FROM user where id = ?";
    private static final String deleteQuery = "DELETE FROM user where id = ?";
    private static final String updateQuery = "UPDATE user SET name = ?, login = ?, password = ?, userType = ? where id = ?";
    final
    JdbcOperations jdbcOperations;
    final
    JdbcTemplate template;

    public UserRepo(@Autowired JdbcOperations jdbcOperations, @Autowired JdbcTemplate template) {
        this.jdbcOperations = jdbcOperations;
        this.template = template;
    }

    @Override
    public User create(User entity) {
        long id = Objects.requireNonNull(new SimpleJdbcInsert(template)
                .withTableName("user")
                .usingColumns("name", "login", "password", "userType")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKeyHolder(Map.of(
                        "name", entity.getName(),
                        "login", entity.getLogin(),
                        "password", entity.getPassword(),
                        "userType", entity.getUserType().getValue()))
                .getKey())
                .longValue();
        entity.setId(id);
        return entity;
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
        return getResult(
                jdbcOperations.queryForRowSet(
                        selectByID,
                        new Object[]{id},
                        new int[]{Types.BIGINT}));
    }

    @Override
    public User update(long id, User entity) {
        Object[] args = new Object[]{
                entity.getName(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getUserType().getValue(),
                id};
        int[] argTypes = new int[]{
                Types.CHAR,
                Types.CHAR,
                Types.CHAR,
                Types.INTEGER,
                Types.BIGINT};
        int rows = jdbcOperations.update(
                updateQuery,
                args,
                argTypes);
        if (rows == 0) {
            return null;
        } else {
            return entity;
        }
    }

    @Override
    public User delete(User entity) {
        int rows = jdbcOperations.update(
                deleteQuery,
                new Object[]{entity.getId()},
                new int[]{Types.BIGINT});
        if (rows == 0) {
            return null;
        } else {
            return entity;
        }
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
