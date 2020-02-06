package ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo;

import ru.epam.javacore.lesson_23_relational_db.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_23_relational_db.homework.common.business.repo.CommonRepo;

import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Optional<Carrier> getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
