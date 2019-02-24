package ua.itea.factoryDao;

import ua.itea.dao.products.MySqlProductsDao;
import ua.itea.dao.products.ProductsDao;
import ua.itea.dao.user.MySqlUserDao;
import ua.itea.dao.user.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MySqlDaoFactory extends DaoFactory {
    private Connection conn = null;

    private String url = "jdbc:mysql://%s/%s?user=%s&password=%s";

    private ResourceBundle config;

    public MySqlDaoFactory() {
        try {
            config = ResourceBundle.getBundle("config." + ResourceBundle.getBundle("config.config").getString("config"));
            String host = config.getString("host");
            String db = config.getString("db");
            String user = config.getString("user");
            String psw = config.getString("psw");
            url = String.format(url, host, db, user, psw);
        } catch (MissingResourceException e) {
            System.err.println("Missing resource in /WEB-INF/config/config\nLoading from standart");
            String host = "localhost";
            String db = "dbitea";
            String user = "mysql";
            String psw = "mysql";
            url = String.format(url, host, db, user, psw);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Connection....");
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Failure...");
            System.out.println("SQLException " + e.getMessage());
            System.out.println("SQLState " + e.getSQLState());
            System.out.println("VendorError..." + e.getErrorCode());
        }
        System.out.println("Connection obtained");
    }

    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao(conn);
    }

    @Override
    public ProductsDao getProductsDao() {
        return new MySqlProductsDao(conn);
    }
}
