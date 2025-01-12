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
    private String categoryName;
    private String categoryDescription;

    // Producent
    private String manufacturerName;

    // Promocja
    private String saleName;
    private String saleStartDate;
    private String saleEndDate;

    // Konstruktor
    public OBMongoProduct(ObjectId id, String name, String details, double price,
                        ObjectId categoryId, ObjectId manufacturerId, ObjectId saleId) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.price = price;
    }
}
