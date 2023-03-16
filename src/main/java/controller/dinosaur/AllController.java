package controller.dinosaur;

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
import java.util.Collection;

@WebServlet("/dinosaur/all")
public class AllController extends HttpServlet {
    private final DinosaurService dinosaurService = new DinosaurService(
            new DinosaurRepository(
                    new ConnectionFactory()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<Dinosaur> dinosaurCollection = dinosaurService.getAll();
            req.setAttribute("dinosaurs", dinosaurCollection);
            req.getRequestDispatcher("/dinosaur/all.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
