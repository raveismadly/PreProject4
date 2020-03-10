package servlets;

import model.User;
import service.Service;
import service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Service service = ServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        User user;
        if (mail.equals("") || password.equals("")) {
            resp.sendRedirect("login");
        } else if (service.validateUser(mail, password)) {
            HttpSession session = req.getSession();

            if ((user = service.getUserByMail(mail)) != null) {
                session.setAttribute("role", user.getRole());
                if (session.getAttribute("role").equals("admin")) {
                    resp.sendRedirect("admin");
                } else {
                    resp.sendRedirect("user");
                }
            }

        } else {
            resp.sendRedirect("login");
        }
    }
}
