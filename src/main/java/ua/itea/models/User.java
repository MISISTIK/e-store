package ua.itea.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {
    @Getter @Setter private String name;
    @Getter @Setter private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
