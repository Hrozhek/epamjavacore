package ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc;

import java.sql.PreparedStatement;

@FunctionalInterface
public interface PreparedStatementIdentityFunc {

    PreparedStatement applyParamsAndGet(PreparedStatement ps) throws Exception;
}
