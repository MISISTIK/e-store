package ua.itea.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Product {
    public Product() {
    }

    @Setter @Getter private int id;
    @Setter @Getter private String name;
    @Setter @Getter private int price;
    @Setter @Getter private String description;
    @Setter @Getter private int category;
}
