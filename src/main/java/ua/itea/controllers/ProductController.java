package ua.itea.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ua.itea.dao.products.ProductsDao;
import ua.itea.factoryDao.DaoFactory;
import ua.itea.models.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private Log log = LogFactory.getLog(getClass());

    private ProductsDao productsDao;

    public ProductController() {
        try {
            productsDao = DaoFactory.getUserProductsDefault();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public List<Product> getProducts() {
      return productsDao.getProducts();
    }

    public List<Product> getProductsByCategory(int cat) {
        return productsDao.getProductsByCategory(cat);
    }

    public Product getProductById(int id) {
        return productsDao.getProductById(id);
    }

}
