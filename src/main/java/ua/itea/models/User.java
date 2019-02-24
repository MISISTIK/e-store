package ua.itea.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {

    @Getter @Setter private int id;
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String name;
    @Getter @Setter private int age;
    @Getter @Setter private String gender;
    @Getter @Setter private String address;

    public User() {
    }

    public User(String login, String password, String name, int age, String gender, String address) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }
}
