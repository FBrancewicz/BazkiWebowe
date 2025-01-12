package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Wspiera AUTO_INCREMENT
    private Integer id;
    private int quantity;
    private String orderDate;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Client client;

    // Getters and Setters
}
