package ru.epam.javacore.lesson_23_relational_db.homework.cargo.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class FoodCargo extends Cargo {

  private LocalDateTime expirationDate;
  private int storeTemperature;

  @Override
  public CargoType getCargoType() {
    return CargoType.FOOD;
  }

  public LocalDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getStoreTemperature() {
    return storeTemperature;
  }

  public void setStoreTemperature(int storeTemperature) {
    this.storeTemperature = storeTemperature;
  }
}
