package ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl.jdbc;

import ru.epam.javacore.lesson_23_relational_db.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.domain.CarrierType;

import java.sql.ResultSet;

public final class CarrierMapper {

  private CarrierMapper() {

  }

  public static Carrier mapCarrier(ResultSet rs) {
    try {
      Carrier carrier = new Carrier();
      carrier.setId(rs.getLong("ID"));
      carrier.setName(rs.getString("NAME"));
      carrier.setAddress(rs.getString("ADDRESS"));
      carrier.setCarrierType(CarrierType.valueOf(rs.getString("ENTITY_TYPE")));

      return carrier;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
