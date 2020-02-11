package ru.epam.javacore.lesson_25_web.lesson.controller;

import ru.epam.javacore.lesson_24_db_web.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_24_db_web.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_24_db_web.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_24_db_web.homework.carrier.service.CarrierService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AAA", urlPatterns = "/cargos")
public class ViewAllCargosController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CargoService cargoService = ServiceHolder.getInstance().getCargoService();

        List<Cargo> all = cargoService.getAll();

        req.setAttribute("allCargos", all);

        goToPage(req, resp, "viewallcargos.jsp");

    }
}
