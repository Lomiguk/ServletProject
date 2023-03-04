package controller;

import entity.Dinosaur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.ConnectionFactory;
import repository.DinosaurRepository;
import service.DinosaurService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

@WebServlet("/dino")
public class dinoTestServlet extends HttpServlet {
    private final DinosaurService dinosaurService = new DinosaurService(new DinosaurRepository(new ConnectionFactory()));
    private final Logger LOGGER = Logger.getLogger(dinosaurService.getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String selectedId = req.getParameter("id");
        Long id = Long.valueOf(selectedId);

        try {
            Dinosaur dinosaur = dinosaurService.getById(id);
            Collection<Dinosaur> dinosaurCollection = dinosaurService.getAll();

            req.setAttribute("dinosaurId", dinosaur.getId());
            req.setAttribute("dinosaurName", dinosaur.getName());

            req.setAttribute("dinosaurs", dinosaurCollection);

            req.getRequestDispatcher("/WEB-INF/pages/dino.jsp").forward(req, resp);
        } catch (ServletException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
