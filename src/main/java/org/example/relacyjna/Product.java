package org.example.relacyjna;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private String id;
    private String name;
    private String details;
    private double price;


    public Product(String id, String name, String details, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
    }

}
