package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/user", "/create", "/delete", "/update"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // HttpServletRequest resp = (HttpServletRequest) response;
        String userRole = (String) req.getSession(false).getAttribute("role");
        if (userRole != null && userRole.equals("admin")) {
            chain.doFilter(request, response);
        } else if (userRole == null) {
            req.setAttribute("message", "Please login");
            req.getRequestDispatcher("/login").forward(request, response);
        } else {
            String user = (String) req.getSession(false).getAttribute("login");
            req.setAttribute("message", "Welcome " + user);
            req.getRequestDispatcher("/user").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
