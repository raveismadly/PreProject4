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

@WebServlet
public class UpdateServlet extends HttpServlet {
    private Service service = ServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        User haveThisUser = service.getUserByMail(mail);
        req.setAttribute("haveThisUser", haveThisUser);
        req.getRequestDispatcher("jsp/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        if (id != null && mail != null && password != null && role != null) {
            service.updateUser(new User(id, mail, password, role));
        }
        resp.sendRedirect("admin");
    }
}
