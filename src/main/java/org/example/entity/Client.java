package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Wspiera AUTO_INCREMENT
    private Integer id;

    private String username;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "client")
    private List<Opinion> opinions;

    @OneToMany(mappedBy = "client")
    private List<OrderedProduct> orderedProducts;

    // Getters and Setters
}