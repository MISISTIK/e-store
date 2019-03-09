package ua.itea.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="login",length = 40)
    private String login;

    @Column(name="password",length = 80)
    private String password;

    @Column(name="name",length = 30)
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="gender", length = 10)
    private String gender;

    @Column(name="address", length = 50)
    private String address;

    public User(String login, String password, String name, int age, String gender, String address) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

}

