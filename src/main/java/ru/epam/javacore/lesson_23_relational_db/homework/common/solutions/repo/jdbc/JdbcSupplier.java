package ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc;

public interface JdbcSupplier<T> {
    T get() throws Exception;
}
