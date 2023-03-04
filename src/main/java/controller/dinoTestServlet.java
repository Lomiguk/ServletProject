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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/dino")
public class dinoTestServlet extends HttpServlet {
    private final DinosaurService dinosaurService = new DinosaurService(new DinosaurRepository(new ConnectionFactory()));
    private final Logger LOGGER = Logger.getLogger(dinosaurService.getClass().getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Long id = Long.valueOf(req.getParameter("id"));
        PrintWriter printWriter = resp.getWriter();
        try{
            Dinosaur dinosaur = dinosaurService.getById(id);
            Collection<Dinosaur> dinosaurCollection = dinosaurService.getAll();
            printWriter.println(String.format("id = %d, name = %s", dinosaur.getId(), dinosaur.getName()));
            printWriter.println("All dinosaurs:");
            for (Dinosaur dino : dinosaurCollection) {
                printWriter.println(String.format("%d %s", dino.getId(), dino.getName()));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "service get exception", e);
        }
    }
}
