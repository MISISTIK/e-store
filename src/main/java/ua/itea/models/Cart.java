package ua.itea.models;

import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import ua.itea.dao.products.ProductsDao;
import ua.itea.factoryDao.DaoFactory;

import java.sql.SQLException;
import java.util.*;

public class Cart {

    @GetMapping
    public String cart() {
        return "ajax";
    }

    private int size = 0;
    private Map<Product, Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public int getSize() {
        return products.size();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void plus(Product p) {
        if (p != null) {
            Set<Product> keys = products.keySet();
            if (keys.contains(p)) {
                products.put(p, products.get(p)+1);
            }
        }
    }

    public void minus(Product p) {
        if (p != null) {
            Set<Product> keys = products.keySet();
            if (keys.contains(p)) {
                products.put(p, products.get(p)-1);
            }
        }
    }

    public void addProduct(Product p) {
        if (p != null) {
            Set<Product> keys = products.keySet();
            if (!keys.contains(p)) {
                products.put(p, 1);
                size += 1;
            }
        }
    }

    public void removeProduct(Product p) {
        if (p != null) {
            Set<Product> keys = products.keySet();
            if (keys.contains(p)) {
                products.remove(p);
                size -= size == 0 ? 0 : 1;
            }
        }
    }
}
