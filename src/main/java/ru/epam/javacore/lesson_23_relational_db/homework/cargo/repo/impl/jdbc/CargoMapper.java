package ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.jdbc;

import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.CargoType;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.ClothersCargo;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain.FoodCargo;

import java.sql.ResultSet;

public final class CargoMapper {

  public static Cargo mapCargo(ResultSet rs) {
    try {
      CargoType cargoType = CargoType.valueOf(rs.getString("ENTITY_TYPE"));
      if (CargoType.CLOTHERS.equals(cargoType)) {
        return mapClothersCargo(rs);
      } else {
        return mapFoodCargo(rs);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  private static FoodCargo mapFoodCargo(ResultSet rs) throws Exception {
    FoodCargo foodCargo = new FoodCargo();
    mapCommonFields(foodCargo, rs);
    foodCargo.setExpirationDate(rs.getTimestamp("FOOD_EXPIRATION_DATE").toLocalDateTime());
    foodCargo.setStoreTemperature(rs.getInt("FOOD_STORE_TEMPERATURE"));
    return foodCargo;
  }

  private static ClothersCargo mapClothersCargo(ResultSet rs) throws Exception {
    ClothersCargo clothersCargo = new ClothersCargo();
    mapCommonFields(clothersCargo, rs);
    clothersCargo.setMaterial(rs.getString("CLOTHERS_SIZE"));
    clothersCargo.setSize(rs.getString("CLOTHERS_MATERIAL"));
    return clothersCargo;
  }

  private static void mapCommonFields(Cargo cargo, ResultSet rs) throws Exception {
    cargo.setId(rs.getLong("ID"));
    cargo.setName(rs.getString("NAME"));
    cargo.setWeight(rs.getInt("WEIGHT"));
  }
}
