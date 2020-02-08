package ru.epam.javacore.lesson_23_relational_db.lesson;

import java.sql.Connection;

public interface A_1_Connectible {
    Connection getConnection() throws Exception;
}
