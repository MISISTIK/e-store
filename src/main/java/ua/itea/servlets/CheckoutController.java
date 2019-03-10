package ua.itea.servlets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.itea.models.Cart;
import ua.itea.models.Product;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static ua.itea.utils.Utils.*;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutController {

    private final static int MAX_WIDTH = 32;
    private final static String h1 = repeatStr("-", MAX_WIDTH);

    private Log log = LogFactory.getLog(getClass());

    @GetMapping
    public String checkout() {
        return "checkout";
    }

    @PostMapping
    @ResponseBody
    public String checkout_post(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        StringBuffer bf = new StringBuffer();
        if (cart != null) {
            Map<Product, Integer> map = cart.getProducts();
            bf.append("<b>").append(alignCenter("E-store", MAX_WIDTH)).append("</b>").append("\n");
            bf.append(alignSides("Checkout for: ", "MISISTIK", MAX_WIDTH)).append("\n");
            bf.append(h1).append("\n");

            int total = 0;
            for (Map.Entry<Product, Integer> e : map.entrySet()) {
                total += e.getValue() * e.getKey().getPrice();
                bf.append(alignCenter(e.getValue() + " x " + e.getKey().getPrice(), MAX_WIDTH)).append("\n");
                bf.append(alignSides(e.getKey().getName(), String.valueOf(e.getValue() * e.getKey().getPrice()), MAX_WIDTH)).append("\n");
            }
            bf.append(h1).append("\n");
            bf.append(alignSides("Discount", "0", MAX_WIDTH)).append("\n");
            bf.append("<b>").append(alignSides("TOTAL: ", String.valueOf(total), MAX_WIDTH)).append("</b>").append("\n");

        }
        return bf.toString();
    }
}
