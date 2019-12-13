package ru.epam.javacore.lesson_5_oop_continue.homework;

import ru.epam.javacore.lesson_5_oop_continue.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_5_oop_continue.homework.storage.Storage;
import ru.epam.javacore.lesson_5_oop_continue.homework.storage.initor.InMemoryStorageInitor;
import ru.epam.javacore.lesson_5_oop_continue.homework.storage.initor.StorageInitor;

import java.util.Arrays;

public class Application {

  private static final String SEPARATOR = "--------------";

  public static void main(String[] args) {
    StorageInitor storageInitor = new InMemoryStorageInitor();
    storageInitor.initStorage();
    printStorageData();
    doSearchOperations();
  }

  private static void printStorageData() {
    System.out.println("ALL CARGOS");
    Storage.printAllCargos();
    printSeparator();

    System.out.println("ALL CARRIERS");
    Storage.printAllCarriers();
    printSeparator();

    System.out.println("ALL TRANSPOORTATIONS");
    Storage.printAllTransportations();
    printSeparator();
  }

  private static void doSearchOperations() {
    System.out.println("SEARCH CARGO BY ID = 1");
    System.out.println(Storage.getCargoById(1L));
    printSeparator();

    System.out.println("SEARCH CARRIER BY ID = 8");
    System.out.println(Storage.getCarrierById(8L));
    printSeparator();

    System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
    System.out.println(Arrays.toString(Storage.getCargosByName("Clothers_Name_1")));
    printSeparator();

    System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
    Carrier[] carriers = Storage.getCarriersByName("Carrier_Name");
    for (Carrier carrier: carriers){
      System.out.println(carrier);
    }
  }

  private static void printSeparator() {
    System.out.println(SEPARATOR);
  }

}
