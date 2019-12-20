package ru.epam.javacore.lesson_8_collections_continue_map.homework.cargo.service;

import ru.epam.javacore.lesson_8_collections_continue_map.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_8_collections_continue_map.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_8_collections_continue_map.homework.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getAll();

    List<Cargo> findByName(String name);

    void update(Cargo cargo);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
