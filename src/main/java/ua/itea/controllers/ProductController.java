package ua.itea.controllers;

import ua.itea.dao.products.ProductsDao;
import ua.itea.factoryDao.DaoFactory;
import ua.itea.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private ProductsDao productsDao;

    public ProductController() {
        try {
            productsDao = DaoFactory.getUserProductsDefault();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
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
