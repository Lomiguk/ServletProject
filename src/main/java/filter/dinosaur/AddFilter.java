package filter.dinosaur;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/dinosaur/add")
public class AddFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest
            , ServletResponse servletResponse
            , FilterChain filterChain)
            throws IOException, ServletException {
        ServletContext servletContext = servletRequest.getServletContext();
        Boolean isAuthorised = (Boolean) servletContext.getAttribute("isAuthorised");
        if (isAuthorised == null || !isAuthorised) {
            servletRequest
                    .getRequestDispatcher("/profile/login")
                    .forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
