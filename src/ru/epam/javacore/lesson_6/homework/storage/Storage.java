package ru.epam.javacore.lesson_6.homework.storage;


import ru.epam.javacore.lesson_6.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_6.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_6.homework.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.lesson_6.homework.transportation.domain.Transportation;

public class Storage {

  private static final int ARRAY_CAPACITY = 10;

  public static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
  public static int cargoIndex = 0;

  public static Carrier[] carriers = new Carrier[ARRAY_CAPACITY];
  public static int carrierIndex = 0;

  public static Transportation[] transportations = new Transportation[ARRAY_CAPACITY];
  public static int transportationIndex = 0;



  public static void printAllCargos() {
    ArrayUtils.printArray(cargos);
  }

  public static void printAllCarriers() {
    ArrayUtils.printArray(carriers);
  }

  public static void addTransportation(Transportation transportation) {
    transportation.setId(IdGenerator.generateId());
    transportations[transportationIndex] = transportation;
    transportationIndex++;

    if (transportationIndex == transportations.length) {
      Transportation[] newTransportations = new Transportation[transportations.length * 2];
      ArrayUtils.copyArray(transportations, newTransportations);
      transportations = newTransportations;
    }
  }

  public static Transportation getTransportationById(long id) {
    for (Transportation transportation : transportations) {
      if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
        return transportation;
      }
    }

    return null;
  }

  public static void printAllTransportations() {
    ArrayUtils.printArray(transportations);
  }

}
