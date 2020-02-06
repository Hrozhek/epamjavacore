package ru.epam.javacore.lesson_23_relational_db.homework.common.business.datasource;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConnectionProvider {
    Connection getConnection() throws SQLException;
}
