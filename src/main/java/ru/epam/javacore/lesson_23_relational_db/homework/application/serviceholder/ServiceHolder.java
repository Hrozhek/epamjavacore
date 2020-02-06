package ru.epam.javacore.lesson_23_relational_db.homework.application.serviceholder;

import ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.CargoArrayRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.CargoCollectionRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.jdbc.CargoRelationalDbRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.service.CargoServiceImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl.CarrierArrayRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl.jdbc.CarrierCollectionRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl.CarrierRelationalDbRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.service.CarrierServiceImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo.impl.TransportationArrayRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo.impl.TransportationCollectionRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo.impl.jdbc.TransportationRelationalDbRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.service.TransportationService;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.service.TransportationServiceImpl;

public final class ServiceHolder {

  private static ServiceHolder instance = null;

  private final CarrierService carrierService;
  private final CargoService cargoService;
  private final TransportationService transportationService;

  private ServiceHolder(StorageType storageType) {
    SimpleServiceHolder initedServiceHolder = getInitedServiceHolder(storageType);
    cargoService = initedServiceHolder.cargoService;
    carrierService = initedServiceHolder.carrierService;
    transportationService = initedServiceHolder.transportationService;
  }

  public static void initServiceHolder(StorageType storageType) {
    ServiceHolder.instance = new ServiceHolder(storageType);
  }

  public static ServiceHolder getInstance() {
    return instance;
  }

  private static class SimpleServiceHolder {

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public SimpleServiceHolder(
        CarrierService carrierService,
        CargoService cargoService,
        TransportationService transportationService) {
      this.carrierService = carrierService;
      this.cargoService = cargoService;
      this.transportationService = transportationService;
    }
  }

  private SimpleServiceHolder getInitedServiceHolder(StorageType storageType) {
    switch (storageType) {

      case ARRAY: {
        return new SimpleServiceHolder(
            new CarrierServiceImpl(new CarrierArrayRepoImpl()),
            new CargoServiceImpl(new CargoArrayRepoImpl()),
            new TransportationServiceImpl(new TransportationArrayRepoImpl()));
      }

      case RELATIONAL_DB: {
        return new SimpleServiceHolder(
            new CarrierServiceImpl(new CarrierRelationalDbRepoImpl()),
            new CargoServiceImpl(new CargoRelationalDbRepoImpl()),
            new TransportationServiceImpl(new TransportationRelationalDbRepoImpl())
        );
      }
      default: {
        return new SimpleServiceHolder(
            new CarrierServiceImpl(new CarrierCollectionRepoImpl()),
            new CargoServiceImpl(new CargoCollectionRepoImpl()),
            new TransportationServiceImpl(new TransportationCollectionRepoImpl()));
      }
    }
  }

  public CarrierService getCarrierService() {
    return carrierService;
  }

  public CargoService getCargoService() {
    return cargoService;
  }

  public TransportationService getTransportationService() {
    return transportationService;
  }
}
