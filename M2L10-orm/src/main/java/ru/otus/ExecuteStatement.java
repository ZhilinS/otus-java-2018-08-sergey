package ru.otus;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface ExecuteStatement {

    void apply(final PreparedStatement statement) throws SQLException;
}
