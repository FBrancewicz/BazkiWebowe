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

    private String categoryName;
    private String categoryDescription;
    private String manufacturerName;
    private String saleName;
    private String saleStartDate;
    private String saleEndDate;

    public Product(String id, String name, String details, double price,
                   String categoryName, String categoryDescription,
                   String manufacturerName,
                   String saleName, String saleStartDate, String saleEndDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.manufacturerName = manufacturerName;
        this.saleName = saleName;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
    }
}