package ru.epam.javacore.lesson_6.homework.cargo.repo;

import ru.epam.javacore.lesson_6.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_6.homework.common.business.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {

  void add(Cargo cargo);

  Cargo getById(long id);

  Cargo[] getByName(String name);
}
