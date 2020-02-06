package ru.epam.javacore.lesson_23_relational_db.homework.application.serviceholder;

import ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.CargoArrayRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.CargoCollectionRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.repo.impl.jdbc.CargoRelationalDbRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_23_relational_db.homework.cargo.service.CargoServiceImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl.CarrierArrayRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl.CarrierCollectionRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.repo.impl.jdbc.CarrierRelationalDbRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_23_relational_db.homework.carrier.service.CarrierServiceImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.common.business.datasource.HikariCpDataSource;
import ru.epam.javacore.lesson_23_relational_db.homework.common.solutions.repo.jdbc.QueryWrapper;
import ru.epam.javacore.lesson_23_relational_db.homework.storage.initor.relationldb.DatabaseConfig;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo.impl.TransportationArrayRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo.impl.TransportationCollectionRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.repo.impl.jdbc.TransportationRelationalDbRepoImpl;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.service.TransportationService;
import ru.epam.javacore.lesson_23_relational_db.homework.transportation.service.TransportationServiceImpl;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ServiceHolder {

  private static final String DATABASE_CONFIG_PATH = "/ru/epam/javacore/lesson_23_relational_db/db_config/config.properties";
  private static ServiceHolder instance = null;

  private final CarrierService carrierService;
  private final CargoService cargoService;
  private final TransportationService transportationService;

  private ServiceHolder(StorageType storageType) throws Exception {
    SimpleServiceHolder initedServiceHolder = getInitedServiceHolder(storageType);
    cargoService = initedServiceHolder.cargoService;
    carrierService = initedServiceHolder.carrierService;
    transportationService = initedServiceHolder.transportationService;
  }

  public static void initServiceHolder(StorageType storageType) throws Exception {
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

  private SimpleServiceHolder getInitedServiceHolder(StorageType storageType) throws Exception {
    switch (storageType) {

      case ARRAY: {
        return new SimpleServiceHolder(
            new CarrierServiceImpl(new CarrierArrayRepoImpl()),
            new CargoServiceImpl(new CargoArrayRepoImpl()),
            new TransportationServiceImpl(new TransportationArrayRepoImpl()));
      }

      case RELATIONAL_DB: {
        prepareDataSourceConfig();
        cleanDb();

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

  private void prepareDataSourceConfig() throws Exception {
    HikariCpDataSource.HikariCpDataSourceBuilder hikariCpDataSourceBuilder = new HikariCpDataSource.HikariCpDataSourceBuilder();
    Map<DatabaseConfig, String> dbConfigs = readDbConfigFromResources();

    dbConfigs.forEach((param, value) -> {
      switch (param) {

        case URL: {
          hikariCpDataSourceBuilder.appendUrl(value);
          break;
        }
        case USER: {
          hikariCpDataSourceBuilder.appendUserName(value);
          break;
        }
        case PASSWORD: {
          hikariCpDataSourceBuilder.appendPassword(value);
          break;
        }

        case DRIVER: {
          hikariCpDataSourceBuilder.appendDriver(value);
          break;
        }
      }
    });
    HikariCpDataSource.init(hikariCpDataSourceBuilder);
  }

  private Map<DatabaseConfig, String> readDbConfigFromResources() throws Exception {
    Properties props = new Properties();
    props.load(this.getClass().getResourceAsStream(DATABASE_CONFIG_PATH));

    Map<DatabaseConfig, String> result = new HashMap<>();
    Arrays.stream(DatabaseConfig.values()).forEach(dbConfig ->
        result.put(dbConfig, props.getProperty(dbConfig.getPropName())));

    return result;
  }

  private void cleanDb() throws Exception {
    Connection connection = null;
    boolean connectionAutoCommitFlag = false;
    try {
      connection = HikariCpDataSource.getInstance().getConnection();
      connectionAutoCommitFlag = connection.getAutoCommit();
      connection.setAutoCommit(false);
      QueryWrapper.executeUpdate("DELETE FROM TRANSPORTATION", connection);
      QueryWrapper.executeUpdate("DELETE FROM CARGO", connection);
      QueryWrapper.executeUpdate("DELETE FROM CARRIER", connection);
      connection.commit();
    } catch (Exception e) {
      if (connection != null) {
        connection.rollback();
      }
      throw e;
    } finally {
      if (connection != null) {
        connection.setAutoCommit(connectionAutoCommitFlag);
        connection.close();
      }
    }

  }

}
