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
public class loginController extends HttpServlet {
    private final ProfileService profileService = new ProfileService(new ProfileRepository(new ConnectionFactory()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/profile/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Profile profile = Profile.builder()
                    .login(req.getParameter("login"))
                    .password(req.getParameter("password"))
                    .build();
            boolean isAuthorised = profileService.checkExisting(profile);
            // HttpSession session = req.getSession();
            // session.setAttribute("isAuthorised", isAuthorised);
            ServletContext servletContext = getServletContext();
            servletContext.setAttribute("isAuthorised", isAuthorised);
            if (isAuthorised){
                  servletContext.setAttribute("userLogin", profile.getLogin());
            }

            //req.getRequestDispatcher("/").forward(req,resp);
            resp.sendRedirect(servletContext.getContextPath()+"/index.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
