package ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl;

import ru.epam.javacore.lesson_23_relational_db.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.CarrierRepo;

import java.util.List;
import java.util.Optional;

public class CarrierRelationalDbRepoImpl implements CarrierRepo {

  @Override
  public Optional<Carrier> getByIdFetchingTransportations(long id) {
    return Optional.empty();
  }

  @Override
  public Carrier[] findByName(String name) {
    return new Carrier[0];
  }

  @Override
  public Optional<Carrier> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public void save(Carrier entity) {

  }

  @Override
  public boolean update(Carrier entity) {
    return false;
  }

  @Override
  public boolean deleteById(Long aLong) {
    return false;
  }

  @Override
  public List<Carrier> getAll() {
    return null;
  }

  @Override
  public int countAll() {
    return 0;
  }
}
