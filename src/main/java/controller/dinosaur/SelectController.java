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

@WebServlet("/dinosaur/select")
public class SelectController extends HttpServlet {
    private final DinosaurService dinosaurService = new DinosaurService(new DinosaurRepository(new ConnectionFactory()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String selectedId = req.getParameter("id");
        Long id = Long.valueOf(selectedId);
        try{
            Dinosaur dinosaur = dinosaurService.getById(id);
            req.setAttribute("dinosaurId", dinosaur.getId());
            req.setAttribute("dinosaurName", dinosaur.getName());

            req.getRequestDispatcher("/dinosaur/select.jsp").forward(req, resp);
        } catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
