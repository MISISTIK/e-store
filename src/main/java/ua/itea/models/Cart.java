package ua.itea.models;

import lombok.Getter;

import java.util.*;

public class Cart {
    private int size;
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

    public void addProduct(Product p) {
        Set<Product> keys = products.keySet();
        int qty = 1;
        if (keys.contains(p)) {
            qty = products.get(p) + 1;
        }
        products.put(p,qty);
        size += 1;
    }
}
