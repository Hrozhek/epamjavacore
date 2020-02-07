package ru.epam.javacore.lesson_24_db_web.lesson.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static ru.epam.javacore.lesson_24_db_web.lesson.db.CustomConnectionPool.getInstance;

public final class DbUtils {
    private DbUtils() {

    }

    public static int executeUpdate(String sql,
                                    JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection con = getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            psConsumer.accept(ps);
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> selectAll(String sql,
                                        JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection con = getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ResultSet resultSet = ps.executeQuery();

            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
