package ru.epam.javacore.lesson_5_oop_continue.homework;

import ru.epam.javacore.lesson_5_oop_continue.homework.cargo.domain.Cargo;

public class Demo {
    public static void main(String[] args) {
        Cargo cargo = new Cargo();
        cargo.setName("A1");
        Storage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A2");
        Storage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A3");
        Storage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A4");
        Storage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A5");
        Storage.addCargo(cargo);
        Storage.printAllCargos();

    }
}
