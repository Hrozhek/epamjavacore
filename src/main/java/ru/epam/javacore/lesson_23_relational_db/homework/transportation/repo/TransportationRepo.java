package ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo;

import ru.epam.javacore.lesson_23_relational_db.homework.common.business.repo.CommonRepo;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo extends CommonRepo<Transportation, Long> {
  List<Transportation> getAllFetchingCargoAndCarrier();
}
