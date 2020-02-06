package ru.epam.javacore.lesson_23_relational_db.homework.cargo.service;

import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_23_relational_db.homework.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
