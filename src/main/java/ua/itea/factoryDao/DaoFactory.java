package ua.itea.factoryDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.itea.dao.products.ProductsDao;
import ua.itea.dao.user.UserDao;

import java.sql.SQLException;

public abstract class DaoFactory {

    private Log log = LogFactory.getLog(getClass());

    private static ApplicationContext ac = new ClassPathXmlApplicationContext("app-context.xml");

    public abstract UserDao getUserDao() throws SQLException;

    public abstract ProductsDao getProductsDao() throws SQLException;

    public static ProductsDao getUserProductsDefault() throws SQLException {
        return ((DaoFactory) ac.getBean("daoBean")).getProductsDao();
    }

    public static UserDao getUserDaoDefault() throws SQLException {
        return ((DaoFactory) ac.getBean("daoBean")).getUserDao();
    }
}
