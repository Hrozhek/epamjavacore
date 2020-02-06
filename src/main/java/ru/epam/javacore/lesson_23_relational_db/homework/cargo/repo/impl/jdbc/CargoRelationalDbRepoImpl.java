package ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.jdbc;


import static ru.epam.javacore.lesson_23_relational_db.homework.storage.Storage.cargoCollection;

import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.CargoType;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.FoodCargo;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.CommonCargoRepo;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.search.CargoSearchCondition;
import ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc.QueryWrapper;
import ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc.ResultSetExtractor;
import ru.epam.javacore.lesson_23_relational_db.homework.storage.IdGenerator;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CargoRelationalDbRepoImpl extends CommonCargoRepo {

  @Override
  public Optional<Cargo> getByIdFetchingTransportations(long id) {
    return findById(id);
  }

  @Override
  public Cargo[] findByName(String name) {
    String sql = "SELECT * FROM CARGO WHERE NAME = ?";
    return QueryWrapper.select(sql, CargoMapper::mapCargo, ps -> {
      ps.setString(1, name);
    }).toArray(new Cargo[0]);
  }

  @Override
  public List<Cargo> search(CargoSearchCondition searchCondition) {
    String sql = "SELECT * FROM CARGO";

    if (searchCondition.needSorting()) {
      String orderBy = searchCondition.getSortFields()
          .stream()
          .map(Enum::toString)
          .collect(Collectors.joining(","));
      sql += " ORDER BY " + orderBy + " " + searchCondition.getOrderType();
    }

    return QueryWrapper.select(sql, (ResultSetExtractor<Cargo>) CargoMapper::mapCargo);
  }

  @Override
  public Optional<Cargo> findById(Long id) {
    String sql = "SELECT * FROM CARGO WHERE ID = ?";
    return QueryWrapper.selectOne(sql,
        CargoMapper::mapCargo,
        ps -> {
          ps.setLong(1, id);
        });
  }

  @Override
  public void save(Cargo cargo) {
    cargo.setId(IdGenerator.generateId());
    if (cargo.getCargoType().equals(CargoType.CLOTHERS)) {
      saveClothersCargo((ClothersCargo) cargo);
    } else if (cargo.getCargoType().equals(CargoType.FOOD)) {
      saveFoodCargo((FoodCargo) cargo);
    }
  }

  private void saveFoodCargo(FoodCargo foodCargo) {
    String sql = "INSERT INTO CARGO "
        + "(ID, "
        + "NAME, "
        + "WEIGHT, "
        + "ENTITY_TYPE, "
        + "FOOD_EXPIRATION_DATE, "
        + "FOOD_STORE_TEMPERATURE)"
        + " VALUES (?, ?, ?, ?, ?, ?)";

    QueryWrapper.executeUpdate(sql, ps -> {
      int index = 0;
      ps.setLong(++index, foodCargo.getId());
      ps.setString(++index, foodCargo.getName());
      ps.setInt(++index, foodCargo.getWeight());
      ps.setString(++index, CargoType.FOOD.toString());
      ps.setTimestamp(++index, Timestamp.valueOf(foodCargo.getExpirationDate()));
      ps.setInt(++index, foodCargo.getStoreTemperature());
    });
  }

  private void saveClothersCargo(ClothersCargo clothersCargo) {
    String sql = "INSERT INTO CARGO "
        + "(ID, "
        + "NAME, "
        + "WEIGHT, "
        + "ENTITY_TYPE, "
        + "CLOTHERS_SIZE, "
        + "CLOTHERS_MATERIAL)"
        + " VALUES (?, ?, ?, ?,?,?)";

    QueryWrapper.executeUpdate(sql, ps -> {
      int index = 0;
      ps.setLong(++index, clothersCargo.getId());
      ps.setString(++index, clothersCargo.getName());
      ps.setInt(++index, clothersCargo.getWeight());
      ps.setString(++index, CargoType.CLOTHERS.toString());
      ps.setString(++index, clothersCargo.getSize());
      ps.setString(++index, clothersCargo.getMaterial());
    });
  }

  @Override
  public boolean update(Cargo entity) {
    return true;
  }

  @Override
  public boolean deleteById(Long id) {
    int affected = QueryWrapper.executeUpdate("DELETE FROM CARGO WHERE ID = ?", ps -> {
      ps.setLong(1, id);
    });

    return affected > 0;
  }

  @Override
  public List<Cargo> getAll() {
    return QueryWrapper
        .select("SELECT * FROM CARGO", (ResultSetExtractor<Cargo>) CargoMapper::mapCargo);
  }

  @Override
  public int countAll() {
    return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM CARGO",
        (rs) -> rs.getInt("CNT")).orElse(0);
  }

}
