package ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc;

@FunctionalInterface
public interface JdbcConsumer<T> {

    void consume(T t) throws Exception;
}
