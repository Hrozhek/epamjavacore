package ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo.impl.jdbc;

import ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc.QueryWrapper;
import ru.epam.javacore.lesson_23_relational_db.homework.storage.IdGenerator;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo.TransportationRepo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class TransportationRelationalDbRepoImpl implements TransportationRepo {

  @Override
  public Optional<Transportation> findById(Long id) {
    String sql = "SELECT * FROM TRANSPORTATION WHERE ID = ?";
    return QueryWrapper.selectOne(sql,
        TransportationMapper::mapTransportation,
        ps -> {
          ps.setLong(1, id);
        });
  }

  @Override
  public void save(Transportation entity) {
    entity.setId(IdGenerator.generateId());
    String sql = "INSERT INTO TRANSPORTATION (ID, "
        + "CARGO_ID, "
        + "CARRIER_ID, "
        + "DESCRIPTION, "
        + "BILL_TO , "
        + "BEGIN_DATE) VALUES (?,?,?,?,?,?)";
    QueryWrapper.executeUpdate(sql, ps -> {
      int index = 0;
      ps.setLong(++index, entity.getId());
      ps.setLong(++index, entity.getCargo().getId());
      ps.setLong(++index, entity.getCarrier().getId());
      ps.setString(++index, entity.getDescription());
      ps.setString(++index, entity.getBillTo());
      ps.setTimestamp(++index, Timestamp.valueOf(entity.getTransportationBeginDate()));
    });
  }

  @Override
  public boolean update(Transportation entity) {
    return false;
  }

  @Override
  public boolean deleteById(Long id) {
    int affected = QueryWrapper.executeUpdate("DELETE FROM TRANSPORTATION WHERE ID = ?", ps -> {
      ps.setLong(1, id);
    });

    return affected > 0;
  }

  @Override
  public List<Transportation> getAll() {
    return QueryWrapper
        .select("SELECT * FROM TRANSPORTATION", TransportationMapper::mapTransportation);
  }

  @Override
  public int countAll() {
    return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM TRANSPORTATION",
        (rs) -> rs.getInt("CNT")).orElse(0);
  }
}
