import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultHandler<T> {

    T handle(final ResultSet set) throws SQLException;
}
