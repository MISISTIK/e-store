package ua.itea.dao.products;

import ua.itea.models.Product;

import java.util.List;

public interface ProductsDao {

    Product getProductById(int id);

    List getProducts();

    List<Product> getProductsByCategory(int category);

}
