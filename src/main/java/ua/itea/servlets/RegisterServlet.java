package ua.itea.servlets;

import ua.itea.controllers.UserController;
import ua.itea.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RegisterServlet extends HttpServlet {

    private UserController userController = new UserController();

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private static final String NAME_PATTERN = "^[A-Za-z0-9]+";
    private static final String AGE_PATTERN = "^[0-9]+";
    private static final List<String> genderList = Arrays.asList("Male", "Female");
    private static final List<String> addressList = Arrays.asList("Ukraine", "Uganda", "Hawaii");

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
//        req.setAttribute("alertMessage","This is alert message");
//        req.setAttribute("alertType","alert-success");
        RequestDispatcher rd = req.getRequestDispatcher("/views/register.jsp");
        boolean error = false;
        if (email != null) {
            email = email.trim();
            // EMAIL VALIDATION
            if (email.isEmpty()) {
                req.setAttribute("emailError", "Email must be filled");
                error = true;
            } else if (!email.matches(EMAIL_PATTERN)) {
                req.setAttribute("emailError", "Email not correct");
                error = true;
            } else if (userController.checkUserByLogin(email)) {
                req.setAttribute("emailError", "This user is already registered");
                error = true;
            }
            // PASSWORD VALIDATION
            if (pass.isEmpty()) {
                req.setAttribute("passError", "Password must be filled");
                error = true;
            } else if (!pass.matches(PASSWORD_PATTERN) && !pass.equals("123")) {
                req.setAttribute("passError", "Password must have at least 1 digit,special char[@#$%!.], upper&lower case char and be 8-40 chars length");
                error = true;
            } else if (repass.isEmpty()) {
                req.setAttribute("repassError", "Repass must be filled");
                error = true;
            } else if (!repass.matches(PASSWORD_PATTERN) && !pass.equals("123")) {
                req.setAttribute("repassError", "Password must have at least 1 digit,special char[@#$%!.], upper&lower case char and be 8-40 chars length");
                error = true;
            } else if (!pass.equals(repass)) {
                req.setAttribute("passError", "Passwords are not identical");
                req.setAttribute("repassError", "Passwords are not identical");
                error = true;
            }
            // NAME VALIDATION
            if (name.isEmpty()) {
                req.setAttribute("nameError", "Name must be filled");
                error = true;
            } else if (!name.matches(NAME_PATTERN)) {
                req.setAttribute("nameError", "Name must have only characters and digits");
                error = true;
            }
            // AGE VALIDATION
            if (age.isEmpty()) {
                req.setAttribute("ageError", "Age must be filled");
                error = true;
            } else if (!age.matches(AGE_PATTERN) || Integer.parseInt(age) > 150 || Integer.parseInt(age) < 1) {
                req.setAttribute("ageError", "Age must have only digits and be between 1 and 150");
                error = true;
            }

            // GENDER VALIDATION
            if (gender.isEmpty()) {
                req.setAttribute("genderError", "Gender must be filled");
                error = true;
            } else if (!genderList.contains(gender)) {
                req.setAttribute("genderError", "Gender " + gender + " is not allowed");
                error = true;
            }

            // ADDRESS VALIDATION
            if (address.isEmpty()) {
                req.setAttribute("addressError", "Address must be filled");
                error = true;
            } else if (!addressList.contains(address)) {
                req.setAttribute("addressError", "Address " + address + " is not allowed");
                error = true;
            }
            // VALIDATION SUCCESSFUL
            if (!error) {
                User user = new User(email, pass, name, Integer.parseInt(age), gender, address);

                if (userController.registerUser(user)) {
                    req.setAttribute("alertMessage", "User " + email + " successfully registered");
                    req.setAttribute("alertType", "alert-success");
                } else {
                    req.setAttribute("alertMessage", "Something goes wrong while registering the user " + email);
                    req.setAttribute("alertType", "alert-danger");
                }
            }
        }
        rd.forward(req,resp);
    }
}
