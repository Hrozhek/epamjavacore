package ru.epam.javacore.lesson_24_db_web.lesson.db;

import java.sql.Connection;

public class CustomConnectionPool {
    private static final CustomConnectionPool INSTANCE = new CustomConnectionPool();

    public Connection getConnection(){
        return null;
    }

    public static CustomConnectionPool getInstance() {
        return INSTANCE;
    }
}
