package ru.epam.javacore.lesson_6.homework.carrier.repo;

import ru.epam.javacore.lesson_6.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_6.homework.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {

  void add(Carrier carrier);

  Carrier getById(long id);

  Carrier[] getByName(String name);

}
