package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Wspiera AUTO_INCREMENT
    private Integer id;

    private String content;
    private int rating;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Product product;

    // Getters and Setters
}
