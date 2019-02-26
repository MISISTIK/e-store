package ua.itea.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FilterServlet implements javax.servlet.Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hreq = (HttpServletRequest) req;
        HttpSession s = hreq.getSession();
        if (s.getAttribute("user") == null) {
            RequestDispatcher rd = req.getRequestDispatcher("/login");
//            req.setAttribute("infoMessage","User not logged in");
            rd.forward(req,resp);
        }

        if (req.getParameter("logout") != null) {
            s.invalidate();
            s = hreq.getSession();
            req.setAttribute("infoMessage","Logout. See you next time");
            req.getRequestDispatcher("/login").forward(req,resp);
        }

        System.out.println(("\nIP:" + req.getRemoteAddr() + "\nTime:" + new Date().toString()));
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {
//        param = config.getInitParameter("param");
    }

}
