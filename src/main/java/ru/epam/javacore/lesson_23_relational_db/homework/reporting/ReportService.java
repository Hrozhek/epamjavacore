package ru.epam.javacore.lesson_23_relational_db.homework.reporting;

import ru.epam.javacore.lesson_23_relational_db.homework.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
