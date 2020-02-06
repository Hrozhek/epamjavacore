package ru.epam.javacore.lesson_23_relational_db.homework.common.business.exception.unchecked;

public class OurCompanyUncheckedException extends RuntimeException {

  public OurCompanyUncheckedException(String message) {
    super(message);
  }
}
