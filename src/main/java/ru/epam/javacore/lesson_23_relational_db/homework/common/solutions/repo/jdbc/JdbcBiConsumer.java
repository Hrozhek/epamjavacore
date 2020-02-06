package ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc;

@FunctionalInterface
public interface JdbcBiConsumer<T1, T2> {
    void consume(T1 t1, T2 t2) throws Exception;
}
