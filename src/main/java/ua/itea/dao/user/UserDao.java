package ua.itea.dao.user;

import ua.itea.models.User;

public interface UserDao {

    boolean createUser(User user);

    boolean checkUserByLogin(String login);

    boolean checkLogin(String login,String password);

    boolean registerUser(User user);

    boolean updatePasswordForUser(String login);

    User getUserById(int id);

    User getUserByEmail(String email);

}

