package ru.epam.javacore.lesson_8_collections_continue_map.homework.cargo.repo;

import ru.epam.javacore.lesson_8_collections_continue_map.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_8_collections_continue_map.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_8_collections_continue_map.homework.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo {

  void add(Cargo cargo);

  Cargo getById(long id);

  Cargo[] findByName(String name);

  List<Cargo> getAll();

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);

  void update(Cargo cargo);
}
