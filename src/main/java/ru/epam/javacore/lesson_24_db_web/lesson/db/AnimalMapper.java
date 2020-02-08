package ru.epam.javacore.lesson_24_db_web.lesson.db;

import java.sql.ResultSet;

public class AnimalMapper {

    public static Animal mapAnimal(ResultSet rs) throws Exception {
        Animal animal = new Animal();
        animal.setId(rs.getLong("ID"));
        animal.setName(rs.getString("NAME"));
        return animal;
    }
}
