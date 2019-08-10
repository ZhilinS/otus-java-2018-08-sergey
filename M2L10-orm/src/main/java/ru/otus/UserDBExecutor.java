package ru.otus;

import ru.otus.models.UserDataSet;

public final class UserDBExecutor {

    private static final String CREATE_TABLE = "CREATE TABLE users (id SERIAL, name text, age INT)";
    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS users";
    private static final String INSERT_USER = "INSERT INTO users(name, age) VALUES(?, ?)";
    private static final String GET_USER = "SELECT * FROM users WHERE id = ?";

    private final DBExecutor<UserDataSet> executor;

    public UserDBExecutor(final DBExecutor<UserDataSet> executor) {
        this.executor = executor;
    }

    public void createTable() {
        this.executor.execute(UserDBExecutor.CREATE_TABLE);
    }

    public void deleteTable() {
        this.executor.execute(UserDBExecutor.DELETE_TABLE);
    }

    public void insert(final String name, final int age) {
        this.executor.save(
            UserDBExecutor.INSERT_USER,
            stmt -> {
                stmt.setString(1, name);
                stmt.setInt(2, age);
            }
        );
    }

    public UserDataSet get(final long id) {
        return this.executor.get(
            UserDBExecutor.GET_USER,
            stmt -> stmt.setLong(1, id),
            result -> new UserDataSet(
                result.getLong("id"),
                result.getString("name"),
                result.getInt("age")
            )
        );
    }
}
