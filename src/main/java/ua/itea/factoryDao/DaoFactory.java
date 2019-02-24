package ua.itea.factoryDao;

import ua.itea.dao.products.ProductsDao;
import ua.itea.dao.user.UserDao;

import java.util.ResourceBundle;

public abstract class DaoFactory {
    public abstract UserDao getUserDao();

    public abstract ProductsDao getProductsDao();

    public static ProductsDao getUserProductsDefault() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ResourceBundle config = ResourceBundle.getBundle("config.config");
        return ((DaoFactory) Class.forName(config.getString("factoryClass")).newInstance()).getProductsDao();
    }

    public static UserDao getUserDaoDefault() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ResourceBundle config = ResourceBundle.getBundle("config.config");
        return ((DaoFactory) Class.forName(config.getString("factoryClass")).newInstance()).getUserDao();
    }
}
