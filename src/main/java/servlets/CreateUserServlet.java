package servlets;

import model.User;
import service.Service;
import service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateUserServlet extends HttpServlet {
    Service service = ServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (mail != null && password != null && role != null) {
            service.addUser(new User(mail, password, role));
        }
        resp.sendRedirect("admin");
    }
}
