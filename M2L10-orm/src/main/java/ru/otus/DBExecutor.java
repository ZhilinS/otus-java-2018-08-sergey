package ru.otus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.Driver;
import org.postgresql.ds.PGConnectionPoolDataSource;
import ru.otus.models.DataSet;

public final class DBExecutor<T extends DataSet> {

    private final String user;
    private final String password;
    private final PGConnectionPoolDataSource pool;

    public DBExecutor() {
        this("otus", "otus", "otus");
    }

    public DBExecutor(
        final String db,
        final String user,
        final String password
    ) {
        this.user = user;
        this.password = password;
        this.pool = new PGConnectionPoolDataSource();
        this.pool.setDatabaseName(db);
    }

    public static void init() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    public void execute(final String sql) {
        try (
            final Connection conn = this.pool
                .getPooledConnection(this.user, this.password)
                .getConnection();
        ) {
            conn.createStatement().execute(sql);
        } catch (final SQLException exception) {
            System.err.println("Something went wrong with insert statement");
            exception.printStackTrace();
        }
    }

    public void save(final String sql, final ExecuteStatement execute) {
        try (
            final Connection conn = this.pool
                .getPooledConnection(this.user, this.password)
                .getConnection();
        ) {
            final PreparedStatement stmt = conn.prepareStatement(sql);
            execute.apply(stmt);
            stmt.execute();
        } catch (final SQLException exception) {
            System.err.println("Something went wrong with insert statement");
            exception.printStackTrace();
        }
    }

    public T get(
        final String sql,
        final ExecuteStatement execute,
        final ResultHandler<T> handler
    ) {
        try (
            final Connection conn = this.pool
                .getPooledConnection(this.user, this.password)
                .getConnection()
        ) {
            final PreparedStatement stmt = conn.prepareStatement(sql);
            execute.apply(stmt);
            final ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                throw new RuntimeException(
                    String.format("Nothing to retrieve from DB. SQL: %s", sql)
                );
            }
            return handler.handle(result);
        } catch (final SQLException exception) {
            System.err.println("Something went wrong with select statement");
            exception.printStackTrace();
        }
        throw new RuntimeException("Nothing found");
    }
}
