package ru.epam.javacore.lesson_12_io_nio.homework.storage.initor;

import ru.epam.javacore.lesson_12_io_nio.homework.storage.initor.fileinitor.TextFileDataInitor;
import ru.epam.javacore.lesson_12_io_nio.homework.storage.initor.fileinitor.XmlDomFileDataInitor;

public final class StorageInitorFactory {

  private StorageInitorFactory() {

  }

  public static StorageInitor getStorageInitor(InitStorageType initStorageType) {
    switch (initStorageType) {

      case MEMORY: {
        return new InMemoryStorageInitor();
      }
      case TEXT_FILE: {
        return new TextFileDataInitor();
      }
      case XML_DOM_FILE:{
        return new XmlDomFileDataInitor();
      }
      default: {
        throw new RuntimeException("Unknown storage init type " + initStorageType);
      }
    }
  }

}
