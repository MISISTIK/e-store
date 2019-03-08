package ua.itea.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class FilterServlet implements javax.servlet.Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hreq = (HttpServletRequest) req;
        HttpSession s = hreq.getSession();
        String uri = ((HttpServletRequest) req).getRequestURI();

        if (uri.startsWith("/static/")) {
            chain.doFilter(req, resp);
            return;
        }

        if (s.getAttribute("user") == null && uri.equals("/register")){
            chain.doFilter(req, resp);
            return;
        }

        if (s.getAttribute("user") == null && uri.equals("/login")){
            chain.doFilter(req, resp);
            return;
        }

        if (s.getAttribute("user") == null) {
            ((HttpServletResponse) resp).sendRedirect("/login");
            return;
        }

        System.out.println(("\nIP:" + req.getRemoteAddr() + "\nTime:" + new Date().toString()));
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {
//        param = config.getInitParameter("param");
    }

}
