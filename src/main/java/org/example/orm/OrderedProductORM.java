package org.example.orm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "OrderedProductORM")
@Table(name = "orderedproduct")
public class OrderedProductORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientORM client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductORM productORM;

}