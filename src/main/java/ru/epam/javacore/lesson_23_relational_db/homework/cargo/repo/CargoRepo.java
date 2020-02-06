package ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo;

import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_23_relational_db.homework.common.business.repo.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

  Optional<Cargo> getByIdFetchingTransportations(long id);

  Cargo[] findByName(String name);

  List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
