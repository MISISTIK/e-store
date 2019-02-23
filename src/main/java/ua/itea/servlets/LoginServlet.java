package ua.itea.servlets;

import ua.itea.service.DBWorker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
            if (email != null) {
                email = email.trim();
                if (email.isEmpty()) {
                    req.setAttribute("emailError", "Email must be filled");
                } else if (pass != null && pass.isEmpty()) {
                    req.setAttribute("passError", "Password must be filled");
                } else {
                    DBWorker db = new DBWorker();
                    try {
                        if (!db.checkUserByLogin(email)) {
                            req.setAttribute("emailError", "User " + email + " not found");
                        } else if (db.checkLogin(email, pass)) {
                            HttpSession s = req.getSession();
                            s.setAttribute("user", email);
                            resp.sendRedirect(req.getContextPath() + "/e-store");
                            return;
                        } else {
                            req.setAttribute("passError", "Password not correct");
                        }
                    } finally {
                        db.close();
                    }
                }
            }

        rd.forward(req, resp);
    }
}
