package ua.itea.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ua.itea.dao.user.UserDao;
import ua.itea.factoryDao.DaoFactory;
import ua.itea.models.User;

import java.sql.SQLException;

public class UserController {

    private Log log = LogFactory.getLog(getClass());

    private UserDao userDao;

    public UserController() {
        try {
            userDao = DaoFactory.getUserDaoDefault();
        } catch (SQLException e) {
            log.error(e);
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
