package ru.epam.javacore.lesson_23_relational_db.homework.common.business.exception.unchecked;

public class SqlException extends OurCompanyUncheckedException{

  public SqlException(Exception cause) {
    super(cause.getMessage());
  }

  public SqlException(String message) {
    super(message);
  }

}
