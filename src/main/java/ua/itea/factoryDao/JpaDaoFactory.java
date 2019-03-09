package ua.itea.factoryDao;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import ua.itea.dao.products.JpaProductsDao;
import ua.itea.dao.products.MySqlProductsDao;
import ua.itea.dao.products.ProductsDao;
import ua.itea.dao.user.JpaUserDao;
import ua.itea.dao.user.MySqlUserDao;
import ua.itea.dao.user.UserDao;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class JpaDaoFactory extends DaoFactory {

    private Log log = LogFactory.getLog(getClass());

    public JpaDaoFactory() {
    }

    @Override
    public UserDao getUserDao() throws SQLException {
        return new JpaUserDao();
    }

    @Override
    public ProductsDao getProductsDao() throws SQLException {
        return new JpaProductsDao();
    }
}
