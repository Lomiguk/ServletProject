package filter.dinosaur;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter("/dinosaur/add")
public class addFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext servletContext = servletRequest.getServletContext();
        //Boolean isAuthorised = (Boolean)servletRequest.getAttribute("isAuthorised");
        Boolean isAuthorised = (Boolean) servletContext.getAttribute("isAuthorised");
        if (isAuthorised == null || !isAuthorised){
            // HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            // httpResponse.sendRedirect("/profile/login");
            servletRequest.getRequestDispatcher("/profile/login").forward(servletRequest,servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse );
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
