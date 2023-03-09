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

@WebServlet("/dinosaur/add")
public class AddController extends HttpServlet {
    private static final long serialVersionID = 1L;

    private final DinosaurService dinosaurService = new DinosaurService(new DinosaurRepository(new ConnectionFactory()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/dinosaur/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dinosaurService.add(Dinosaur.builder()
                    .typeOfDinosaurId(Long.parseLong(req.getParameter("type_of_dinosaur_id")))
                    .name(req.getParameter("dinosaur_name"))
                    .attractionId(Long.parseLong(req.getParameter("attraction_id")))
                    .build());
            req.getRequestDispatcher("/WEB-INF/dinosaur/add.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
