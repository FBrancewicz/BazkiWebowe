package org.example.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Wspiera AUTO_INCREMENT
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;

    // Getters and Setters
}
