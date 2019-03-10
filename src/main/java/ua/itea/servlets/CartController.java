package ua.itea.servlets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.itea.dao.products.ProductsDao;
import ua.itea.factoryDao.DaoFactory;
import ua.itea.models.Cart;
import ua.itea.models.Product;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    private Log log = LogFactory.getLog(getClass());
    private ProductsDao productController;

    {
        try {
            productController = DaoFactory.getUserProductsDefault();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    @GetMapping
    public String cart(Model model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            model.addAttribute("products",cart.getProducts());
        }
        return "cart";
    }

    @GetMapping(value = "/remove/{id}")
    public String cart_remove(@PathVariable int id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeProduct(productController.getProductById(id));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    @PostMapping
    @ResponseBody
    public String cart_post(@RequestBody String str, HttpSession session) {
        JSONObject json = new JSONObject(str);
        int id = json.getInt("id");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addProduct(productController.getProductById(id));
        session.setAttribute("cart", cart);
        return new JSONObject()
                .put("size", cart.getSize())
                .toString();
    }

    @PostMapping(value = "/minus/{id}")
    @ResponseBody
    public String cart_minus(@PathVariable int id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.minus(productController.getProductById(id));
        session.setAttribute("cart", cart);
        return "OK";
    }

    @PostMapping(value = "/plus/{id}")
    @ResponseBody
    public String cart_plus(@PathVariable int id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.plus(productController.getProductById(id));
        session.setAttribute("cart", cart);
        return "OK";
    }

    @PostMapping(value = "/size")
    @ResponseBody
    public String cart_size(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return new JSONObject()
                .put("size", cart.getSize())
                .toString();
    }

}
