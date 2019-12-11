package ru.epam.javacore.lesson_5_oop_continue.homework;

import ru.epam.javacore.lesson_5_oop_continue.homework.cargo.domain.Cargo;

public class Storage {
    private static final int ARRAY_CAPACITY = 2;

    private static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
    private static int cargoIndex = 0;

    public static void addCargo(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        cargos[cargoIndex] = cargo;
        cargoIndex++;

        if (cargoIndex % (ARRAY_CAPACITY - 1) == 0) {
            Cargo[] newCargos = new Cargo[cargoIndex + ARRAY_CAPACITY];
            copyCargoArray(cargos, newCargos);
            cargos = newCargos;
        }
    }

    private static void copyCargoArray(Cargo[] src, Cargo[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }

    public static void printAllCargos() {
        for (Cargo cargo: cargos){
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }
}
