package ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl.jdbc;


import static ru.epam.javacore.lesson_23_relational_db.homework.storage.Storage.carrierCollection;

import ru.epam.javacore.lesson_23_relational_db.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc.QueryWrapper;
import ru.epam.javacore.lesson_23_relational_db.homework.storage.IdGenerator;

import java.util.List;
import java.util.Optional;

public class CarrierCollectionRepoImpl implements CarrierRepo {

  @Override
  public void save(Carrier carrier) {
    carrier.setId(IdGenerator.generateId());
    String sql = "INSERT INTO CARRIER (ID,NAME,ADDRESS, ENTITY_TYPE) VALUES (?,?,?,?)";
    QueryWrapper.executeUpdate(sql, ps -> {
      int index = 0;
      ps.setLong(++index, carrier.getId());
      ps.setString(++index, carrier.getName());
      ps.setString(++index, carrier.getAddress());
      ps.setString(++index, carrier.getCarrierType().toString());
    });
  }

  @Override
  public Optional<Carrier> findById(Long id) {
    String sql = "SELECT * FROM CARRIER WHERE ID = ?";
    return QueryWrapper.selectOne(sql,
        CarrierMapper::mapCarrier,
        ps -> {
          ps.setLong(1, id);
        });

  }

  @Override
  public Optional<Carrier> getByIdFetchingTransportations(long id) {
    return findById(id);
  }

  @Override
  public Carrier[] findByName(String name) {
    String sql = "SELECT * FROM CARRIER WHERE NAME = ?";
    return QueryWrapper.select(sql, CarrierMapper::mapCarrier).toArray(new Carrier[0]);
  }

  @Override
  public boolean deleteById(Long id) {
    int affected = QueryWrapper.executeUpdate("DELETE FROM CARRIER WHERE ID = ?", ps -> {
      ps.setLong(1, id);
    });

    return affected > 0;
  }

  @Override
  public List<Carrier> getAll() {
    return QueryWrapper.select("SELECT * FROM CARRIER", CarrierMapper::mapCarrier);
  }

  @Override
  public int countAll() {
    return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM CARRIER",
        (rs) -> rs.getInt("CNT")).orElse(0);
  }

  @Override
  public boolean update(Carrier carrier) {
    return true;
  }

}
