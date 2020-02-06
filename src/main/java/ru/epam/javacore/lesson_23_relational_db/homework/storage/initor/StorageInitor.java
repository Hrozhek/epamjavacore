package ru.epam.javacore.lesson_23_relational_db.homework.storage.initor;

import ru.epam.javacore.lesson_23_relational_db.homework.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
  void initStorage() throws InitStorageException;
}
