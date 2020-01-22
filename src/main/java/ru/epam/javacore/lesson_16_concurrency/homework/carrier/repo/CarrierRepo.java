package ru.epam.javacore.lesson_16_concurrency.homework.carrier.repo;

import ru.epam.javacore.lesson_16_concurrency.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_16_concurrency.homework.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

  Carrier getByIdFetchingTransportations(long id);

  Carrier[] findByName(String name);

}
