package ua.itea.controllers;

import ua.itea.models.Product;
import ua.itea.service.DBWorker;

import java.util.List;

public class PropductController {
    private DBWorker dbWorker;

    public PropductController() {
        this.dbWorker = new DBWorker();
    }

    public List<Product> getProducts(){
        return dbWorker.getProducts();
    }

    public List<Product> getProductsByCategory(int cat) {
        return dbWorker.getProductsByCategory(cat);
    }

    public Product getProductById(int id){
        return dbWorker.getProductById(id);
    }

}
