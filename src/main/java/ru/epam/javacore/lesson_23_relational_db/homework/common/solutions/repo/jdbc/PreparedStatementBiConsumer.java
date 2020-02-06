package ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc;

import java.sql.PreparedStatement;

public interface PreparedStatementBiConsumer<T> extends JdbcBiConsumer<PreparedStatement, T> {
}
