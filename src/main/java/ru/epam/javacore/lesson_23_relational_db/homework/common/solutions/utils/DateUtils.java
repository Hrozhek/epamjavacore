package ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {

  private static final String PATTERN = "dd.MM.yyyy";

  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

  private DateUtils() {

  }

  public static Date utilDateValueOf(String dateStr, String pattern) throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat(pattern);
    return dateFormat.parse(dateStr);
  }

  public static Date utilDateValueOf(String dateStr) throws ParseException {
    return utilDateValueOf(dateStr, PATTERN);
  }

  public static LocalDateTime valueOf(String dateStr) {
    return LocalDateTime.parse(dateStr, formatter);
  }

}
