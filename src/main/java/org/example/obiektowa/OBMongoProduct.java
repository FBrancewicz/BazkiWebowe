package org.example.obiektowa;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;


@Getter
@Setter
public class OBMongoProduct {
    private ObjectId id;
    private String name;
    private String details;
    private double price;

    // Kategorie
    private OBCategory category;

    // Producent
    private OBManufacturer manufacturer;

    // Promocja
    private OBSale sale;

    // Konstruktor
    public OBMongoProduct(ObjectId id, String name, String details, double price) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.price = price;
    }
}
