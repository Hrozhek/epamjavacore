package ru.epam.javacore.lesson_24_db_web.lesson.db;

public interface JdbcConsumer<T> {
    void accept(T t) throws Exception;
}
