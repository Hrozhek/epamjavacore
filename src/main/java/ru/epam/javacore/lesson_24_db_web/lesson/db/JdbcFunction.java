package ru.epam.javacore.lesson_24_db_web.lesson.db;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}
