package ru.epam.javacore.lesson_18_19_20_java_8.lessons.lesson_2.a_3_converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class A_3_StringToIntConverterTest {

  static A_3_StringToIntConverter converter;

  @BeforeAll
  public static void initConverter() {
    converter = new A_3_StringToIntConverter();
  }

  @Test
  void convertValidInt() {
    Integer result = converter.convert("1");

    assertTrue(result == 1);
  }

  @Test
  void convertValidNegativeInt() {
    Integer result = converter.convert("-1");

    assertTrue(result == -1);
  }

  @Test
  void convertNotValidInt() {
    Integer result = converter.convert("ivan");

    assertNull(result);
  }
}