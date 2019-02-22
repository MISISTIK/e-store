package ua.itea.servlets;

import ua.itea.models.Cart;
import ua.itea.models.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartServlet extends HttpServlet {
    public CartServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        Product p = new Product();
        p.setId(Integer.parseInt(req.getParameter("buy")));
        cart.addProduct(p);
        session.setAttribute("cart",cart);
        resp.sendRedirect(req.getContextPath() + "/e-store");
    }
}
