package ua.itea.servlets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.itea.controllers.ProductController;
import ua.itea.dao.user.UserDao;
import ua.itea.factoryDao.DaoFactory;
import ua.itea.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Controller
public class DispatcherServlet {
    private Log log = LogFactory.getLog(getClass());

    private UserDao userController;

    {
        try {
            userController = DaoFactory.getUserDaoDefault();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private ProductController productController = new ProductController();


    @RequestMapping(value = "/products")
    public String products(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String cat,
            Model model) {
        if (id != null && id.matches("\\d")) {
            model.addAttribute("product", productController.getProductById(Integer.parseInt(id)));
            return "redirect:/product_details";
        } else if (cat != null && cat.matches("\\d")) {
            model.addAttribute("products", productController.getProductsByCategory(Integer.parseInt(cat)));
            return "products";
        } else {
            model.addAttribute("products", productController.getProducts());
            return "products";
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session, HttpServletRequest req) {
        session.invalidate();
        session = req.getSession();
        req.setAttribute("infoMessage","Logout. See you next time");
        return "/login";
    }


    @GetMapping(value = "/")
    public String root(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
//            model.addAttribute("products", productController.getProducts());
            return "redirect:/products";
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String login_get(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
//            model.addAttribute("products", productController.getProducts());
            return "/products";
        }
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login_post(@RequestParam String email,
                             @RequestParam String pass,
                             Model model,
                             HttpSession session
    ) {
        if (email != null) {
            email = email.trim();
            if (email.isEmpty()) {
                model.addAttribute("emailError", "Email must be filled");
            } else if (pass != null && pass.isEmpty()) {
                model.addAttribute("passError", "Password must be filled");
            } else {
                if (!userController.checkUserByLogin(email)) {
                    model.addAttribute("emailError", "User " + email + " not found");
                } else if (userController.checkLogin(email, pass)) {
                    session.setAttribute("user", userController.getUserByEmail(email).getName());
                    return "redirect:/products";
                } else {
                    model.addAttribute("passError", "Password not correct");
                }
            }
        }
        return "login";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String register_post(
            @RequestParam String email,
            @RequestParam String pass,
            @RequestParam String repass,
            @RequestParam String name,
            @RequestParam String age,
            @RequestParam String gender,
            @RequestParam String address,
            Model model) {
//        req.setAttribute("alertMessage","This is alert message");
//        req.setAttribute("alertType","alert-success");
        boolean error = false;
        if (email != null) {
            email = email.trim();
            // EMAIL VALIDATION
            if (email.isEmpty()) {
                model.addAttribute("emailError", "Email must be filled");
                error = true;
            } else if (!email.matches(EMAIL_PATTERN)) {
                model.addAttribute("emailError", "Email not correct");
                error = true;
            } else if (userController.checkUserByLogin(email)) {
                model.addAttribute("emailError", "This user is already registered");
                error = true;
            }
            // PASSWORD VALIDATION
            if (pass.isEmpty()) {
                model.addAttribute("passError", "Password must be filled");
                error = true;
            } else if (!pass.matches(PASSWORD_PATTERN) && !pass.equals("123")) {
                model.addAttribute("passError", "Password must have at least 1 digit,special char[@#$%!.], upper&lower case char and be 8-40 chars length");
                error = true;
            } else if (repass.isEmpty()) {
                model.addAttribute("repassError", "Repass must be filled");
                error = true;
            } else if (!repass.matches(PASSWORD_PATTERN) && !pass.equals("123")) {
                model.addAttribute("repassError", "Password must have at least 1 digit,special char[@#$%!.], upper&lower case char and be 8-40 chars length");
                error = true;
            } else if (!pass.equals(repass)) {
                model.addAttribute("passError", "Passwords are not identical");
                model.addAttribute("repassError", "Passwords are not identical");
                error = true;
            }
            // NAME VALIDATION
            if (name.isEmpty()) {
                model.addAttribute("nameError", "Name must be filled");
                error = true;
            } else if (!name.matches(NAME_PATTERN) || name.length() > 20) {
                model.addAttribute("nameError", "Name must have only characters and digits and be < 20 chars");
                error = true;
            }
            // AGE VALIDATION
            if (age.isEmpty()) {
                model.addAttribute("ageError", "Age must be filled");
                error = true;
            } else if (!age.matches(AGE_PATTERN) || Integer.parseInt(age) > 150 || Integer.parseInt(age) < 1) {
                model.addAttribute("ageError", "Age must have only digits and be between 1 and 150");
                error = true;
            }

            // GENDER VALIDATION
            if (gender.isEmpty()) {
                model.addAttribute("genderError", "Gender must be filled");
                error = true;
            } else if (!genderList.contains(gender)) {
                model.addAttribute("genderError", "Gender " + gender + " is not allowed");
                error = true;
            }

            // ADDRESS VALIDATION
            if (address.isEmpty()) {
                model.addAttribute("addressError", "Address must be filled");
                error = true;
            } else if (!addressList.contains(address)) {
                model.addAttribute("addressError", "Address " + address + " is not allowed");
                error = true;
            }
            // VALIDATION SUCCESSFUL
            if (!error) {
                User user = new User(email, pass, name, Integer.parseInt(age), gender, address);

                if (userController.registerUser(user)) {
                    model.addAttribute("alertMessage", "User " + email + " successfully registered");
                    model.addAttribute("alertType", "alert-success");
                } else {
                    model.addAttribute("alertMessage", "Something goes wrong while registering the user " + email);
                    model.addAttribute("alertType", "alert-danger");
                }
            }
        }
        return "register";
    }

    final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private static final String NAME_PATTERN = "^[A-Za-z0-9]+";
    private static final String AGE_PATTERN = "^[0-9]+";
    private static final List<String> genderList = Arrays.asList("Male", "Female");
    private static final List<String> addressList = Arrays.asList("Ukraine", "Uganda", "Hawaii");
}
