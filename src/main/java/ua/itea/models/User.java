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
    @Getter @Setter private String comment;

    public User() {
    }

}
