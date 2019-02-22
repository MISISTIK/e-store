package ua.itea.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Filter implements javax.servlet.Filter {
    private String param;

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        PrintWriter out = new PrintWriter(new File("MyLog.txt"));
        HttpServletRequest hreq = (HttpServletRequest) req;
        HttpSession s = hreq.getSession();
        if (s.getAttribute("user") == null) {
            RequestDispatcher rd = req.getRequestDispatcher("/404");
            rd.forward(req,resp);
        }
        out.write("param1 = " + param + "\nIP:" + req.getRemoteAddr() + "\nTime:" + new Date().toString());
        System.out.println(("param1 = " + param + "\nIP:" + req.getRemoteAddr() + "\nTime:" + new Date().toString()));
        out.close();
        chain.doFilter(req, resp);
        out.close();
    }

    public void init(FilterConfig config) throws ServletException {
        param = config.getInitParameter("param");
    }

}
