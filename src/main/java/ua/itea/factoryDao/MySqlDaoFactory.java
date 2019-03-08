package ua.itea.factoryDao;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import ua.itea.dao.products.MySqlProductsDao;
import ua.itea.dao.products.ProductsDao;
import ua.itea.dao.user.MySqlUserDao;
import ua.itea.dao.user.UserDao;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class MySqlDaoFactory extends DaoFactory {

    private Log log = LogFactory.getLog(getClass());

    @Getter @Setter
    public DataSource dataSource;

    public MySqlDaoFactory() {
    }

    @Override
    public UserDao getUserDao() throws SQLException {
        return new MySqlUserDao(dataSource.getConnection());
    }

    @Override
    public ProductsDao getProductsDao() throws SQLException {
        return new MySqlProductsDao(dataSource.getConnection());
    }
}
