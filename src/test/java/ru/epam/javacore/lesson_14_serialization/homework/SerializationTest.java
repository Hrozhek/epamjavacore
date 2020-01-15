package ru.epam.javacore.lesson_14_serialization.homework;


import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static ru.epam.javacore.lesson_14_serialization.homework.common.solutions.comparator.SimpleComparator.LONG_COMPARATOR;
import static ru.epam.javacore.lesson_14_serialization.homework.common.solutions.comparator.SimpleComparator.STRING_COMPARATOR;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import ru.epam.javacore.lesson_14_serialization.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_14_serialization.homework.cargo.domain.FoodCargo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SerializationTest {

  @Test
  public void testSerializationFoodCargo() throws Exception {
    Path path = null;
    try {
      FoodCargo cargo = prepareFood();
      path = Files.createTempFile("temp", "food");
      String pathToFile = path.toAbsolutePath().toString();

      serializeToFile(cargo, pathToFile);
      FoodCargo deserialized = readSerializedObjectFromFile(pathToFile);

      assertTrue(areFoodEntitiesEquals(cargo, deserialized));
    } finally {
      deleteFile(path);
    }
  }

  @Test
  public void testSerializationFoodCargos() throws Exception {
    Path path = null;
    try {
      path = Files.createTempFile("temp", "foods");
      String pathToFile = path.toAbsolutePath().toString();
      List<FoodCargo> foods = Arrays.asList(prepareFood(), prepareFood());
      serializeToFile(foods, pathToFile);
      List<FoodCargo> deserialized = readSerializedObjectFromFile(pathToFile);

      assertTrue(areFoodEntitiesEquals(foods, deserialized));
    } finally {
      deleteFile(path);
    }
  }

  @Test
  public void testSerializationFoodNullCargo() throws Exception {
    Path path = null;
    try {
      path = Files.createTempFile("temp", "food_null");
      String pathToFile = path.toAbsolutePath().toString();
      serializeToFile(null, pathToFile);
      Object deserialized = readSerializedObjectFromFile(pathToFile);

      assertNull(deserialized);
    } finally {
      deleteFile(path);
    }
  }

  @Test
  public void testSerializationClothersCargo() throws Exception {
    Path path = null;
    try {
      ClothersCargo clothers = prepareClothers();
      path = Files.createTempFile("temp", "clothers");
      String pathToFile = path.toAbsolutePath().toString();
      serializeToFile(clothers, pathToFile);
      ClothersCargo deserialized = readSerializedObjectFromFile(pathToFile);

      assertTrue(areClotherEntitiesEquals(clothers, deserialized));
    } finally {
      deleteFile(path);
    }
  }

  @Test
  public void testSerializationClothersCargos() throws Exception {
    Path path = null;
    try {
      List<ClothersCargo> clothers = Arrays.asList(prepareClothers(), prepareClothers());
      path = Files.createTempFile("temp", "clothers_multi");
      String pathToFile = path.toAbsolutePath().toString();

      serializeToFile(clothers, pathToFile);
      List<ClothersCargo> deserialized = readSerializedObjectFromFile(pathToFile);

      assertTrue(areClotherEntitiesEquals(clothers, deserialized));
    } finally {
      deleteFile(path);
    }
  }

  @Test
  public void testSerializationClothersNullCargos() throws Exception {
    Path path = null;
    try {
      path = Files.createTempFile("temp", "clothers_multi");
      String pathToFile = path.toAbsolutePath().toString();

      serializeToFile(null, pathToFile);
      Object deserialized = readSerializedObjectFromFile(pathToFile);
      assertNull(deserialized);
    } finally {
      deleteFile(path);
    }
  }

  private <T> void serializeToFile(T entity, String file) throws Exception {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
      outputStream.writeObject(entity);
    }
  }

  private <T> T readSerializedObjectFromFile(String file) throws Exception {
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
      return (T) objectInputStream.readObject();
    }
  }

  private FoodCargo prepareFood() {
    FoodCargo food = new FoodCargo();
    food.setId(randLong());
    food.setName(randString());
    food.setWeight(randInt());
    food.setStoreTemperature(randInt());
    food.setExpirationDate(new Date());

    return food;
  }

  private ClothersCargo prepareClothers() {
    ClothersCargo clothers = new ClothersCargo();
    clothers.setName(randString());
    clothers.setId(randLong());
    clothers.setSize(randString());
    clothers.setWeight(randInt());
    clothers.setMaterial(randString());

    return clothers;
  }

  private String randString() {
    return RandomStringUtils.randomAlphabetic(5);
  }

  private int randInt() {
    return RandomUtils.nextInt();
  }

  private long randLong() {
    return RandomUtils.nextLong();
  }

  private boolean areFoodEntitiesEquals(FoodCargo food1, FoodCargo food2) {
    if (food1 == null && food2 == null) {
      return true;
    } else if (food1 != null && food2 == null) {
      return false;
    } else if (food1 == null) {
      return false;
    } else {
      return STRING_COMPARATOR.compare(food1.getName(), food2.getName()) == 0
          && LONG_COMPARATOR.compare(food1.getId(), food2.getId()) == 0
          && food1.getWeight() == food2.getWeight()
          && food1.getStoreTemperature() == food2.getStoreTemperature();
      //continue in this way
    }
  }

  private boolean areFoodEntitiesEquals(List<FoodCargo> foods1, List<FoodCargo> foods2) {
    if (foods1 == null && foods2 == null) {
      return true;
    } else if (foods1 != null && foods2 == null) {
      return false;
    } else if (foods1 == null) {
      return false;
    } else if (foods1.size() != foods2.size()) {
      return false;
    } else {
      for (int i = 0; i < foods1.size(); i++) {
        if (!areFoodEntitiesEquals(foods1.get(i), foods2.get(i))) {
          return false;
        }
      }

      return true;
    }
  }

  private boolean areClotherEntitiesEquals(ClothersCargo clother1, ClothersCargo clother2) {
    if (clother1 == null && clother2 == null) {
      return true;
    } else if (clother1 != null && clother2 == null) {
      return false;
    } else if (clother1 == null) {
      return false;
    } else {
      return STRING_COMPARATOR.compare(clother1.getName(), clother2.getName()) == 0
          && LONG_COMPARATOR.compare(clother1.getId(), clother2.getId()) == 0
          && STRING_COMPARATOR.compare(clother1.getMaterial(), clother2.getMaterial()) == 0;
      //continue in this way
    }
  }

  private boolean areClotherEntitiesEquals(List<ClothersCargo> clothers1,
      List<ClothersCargo> clothers2) {
    if (clothers1 == null && clothers2 == null) {
      return true;
    } else if (clothers1 != null && clothers2 == null) {
      return false;
    } else if (clothers1 == null) {
      return false;
    } else if (clothers1.size() != clothers2.size()) {
      return false;
    } else {
      for (int i = 0; i < clothers1.size(); i++) {
        if (!areClotherEntitiesEquals(clothers1.get(i), clothers2.get(i))) {
          return false;
        }
      }

      return true;
    }
  }

  private void deleteFile(Path path) {
    if (path != null && path.toFile().isFile()) {
      try {
        Files.delete(path);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
