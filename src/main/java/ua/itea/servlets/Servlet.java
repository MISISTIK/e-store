package ua.itea.servlets;

import ua.itea.controllers.UserController;
import ua.itea.models.Product;
import ua.itea.models.User;
import ua.itea.service.DBWorker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    final static String viewsDir = "/WEB-INF/views/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cat = req.getParameter("cat");
        String id = req.getParameter("id");
        DBWorker dbWorker = new DBWorker();
        RequestDispatcher rd = null;
        if (id != null && id.matches("\\d")) {
            rd = req.getRequestDispatcher(viewsDir + "product_details.jsp");
            req.setAttribute("product", dbWorker.getProductById(Integer.parseInt(id)));
        } else if (cat != null && cat.matches("\\d")) {
            rd = req.getRequestDispatcher(viewsDir + "products.jsp");
            req.setAttribute("products", dbWorker.getProductsByCategory(Integer.parseInt(cat)));
        } else {
            rd = req.getRequestDispatcher(viewsDir + "products.jsp");
            req.setAttribute("products", dbWorker.getProducts());
        }
        rd.forward(req,resp);
        dbWorker.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
