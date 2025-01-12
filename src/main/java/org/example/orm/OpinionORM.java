package org.example.orm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "OpinionORM")
@Table(name = "opinion")
public class OpinionORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientORM client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductORM productORM;
}