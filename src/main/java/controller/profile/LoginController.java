package controller.profile;

import entity.Profile;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import repository.ConnectionFactory;
import repository.ProfileRepository;
import service.ProfileService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profile/login")
public class LoginController extends HttpServlet {
    private final ProfileService profileService = new ProfileService(new ProfileRepository(new ConnectionFactory()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/profile/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            Profile profile = Profile.builder()
                    .login(req.getParameter("login"))
                    .password(req.getParameter("password"))
                    .build();
            boolean isAuthorised = profileService.checkExisting(profile);

            ServletContext servletContext = getServletContext();
            servletContext.setAttribute("isAuthorised", isAuthorised);
            if (isAuthorised){
                  req.getSession().setAttribute("userLogin", profile.getLogin());
            }

            resp.sendRedirect(servletContext.getContextPath()+"/index.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
