package ru.epam.javacore.lesson_12_io_nio.homework.storage.initor;

import ru.epam.javacore.lesson_12_io_nio.homework.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
  void initStorage() throws InitStorageException;
}
