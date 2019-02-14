package ua.itea.controllers;

import ua.itea.models.User;

public class UserController {
    private User user;

    public UserController() {
    }

    public User getUser() {
        user = new User();
        user.setName("user name");
        user.setAge(12);
        return user;
    }
}
