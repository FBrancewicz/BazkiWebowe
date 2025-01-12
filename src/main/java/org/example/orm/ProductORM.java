package org.example.orm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ProductORM")
@Table(name = "product")
public class ProductORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String details;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryORM category;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private ManufacturerORM manufacturer;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleORM saleORM;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientORM client;

}
