package ru.epam.javacore.lesson_25_web.lesson.controller;

import ru.epam.javacore.lesson_24_db_web.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_24_db_web.homework.application.serviceholder.StorageType;
import ru.epam.javacore.lesson_24_db_web.homework.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.lesson_24_db_web.homework.storage.initor.InitStorageType;
import ru.epam.javacore.lesson_24_db_web.homework.storage.initor.StorageInitor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.epam.javacore.lesson_24_db_web.homework.storage.initor.StorageInitorFactory.getStorageInitor;

@WebServlet(name = "SelectStorage", urlPatterns = "/selectstorage")
public class SelectStorageController extends BaseController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String storageType = req.getParameter("storageType");

            if (storageType.equals("BULLSHIT")){
                RequestDispatcher dispatcher = req
                        .getRequestDispatcher("index.jsp");
                req.setAttribute("error", "SELECT STORAGE!!!!");
                dispatcher.forward(req, resp);
            }
            if (storageType.equals("DB")) {
               ServiceHolder.initServiceHolder(StorageType.RELATIONAL_DB);
               StorageInitor storageInitor = getStorageInitor(InitStorageType.SQL_SCRIPTS);
                storageInitor.initStorage();

                resp.sendRedirect("cargos");
            } else {
                this.goToPage(req, resp, "viewallcargos.jsp");
            }
        } catch (Exception e) {
            this.goToPage(req, resp, "error.jsp");
        }
    }
}
