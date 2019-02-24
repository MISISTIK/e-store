package ua.itea.servlets;

import ua.itea.controllers.ProductController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductServlet extends HttpServlet {

    final static String viewsDir = "/views/";

    private ProductController productController = new ProductController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = null;

        String cat = req.getParameter("cat");
        String id = req.getParameter("id");

        if (id != null && id.matches("\\d")) {
            rd = req.getRequestDispatcher(viewsDir + "product_details.jsp");
            req.setAttribute("product", productController.getProductById(Integer.parseInt(id)));
        } else if (cat != null && cat.matches("\\d")) {
            rd = req.getRequestDispatcher(viewsDir + "products.jsp");
            req.setAttribute("products", productController.getProductsByCategory(Integer.parseInt(cat)));
        } else {
            rd = req.getRequestDispatcher(viewsDir + "products.jsp");
            req.setAttribute("products", productController.getProducts());
        }

        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
