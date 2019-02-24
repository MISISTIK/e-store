package ua.itea.controllers;

import ua.itea.dao.user.UserDao;
import ua.itea.factoryDao.DaoFactory;
import ua.itea.models.User;

public class UserController {
    private UserDao userDao;

    public UserController() {
        try {
            userDao = DaoFactory.getUserDaoDefault();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserByLogin(String login) {
        return userDao.checkUserByLogin(login);
    }

    public boolean registerUser(User user) {
        return userDao.registerUser(user);
    }

    public boolean checkLogin(String login, String password) {
        return userDao.checkLogin(login, password);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
