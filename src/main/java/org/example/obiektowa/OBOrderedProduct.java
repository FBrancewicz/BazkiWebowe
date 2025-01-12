package org.example.obiektowa;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class OBOrderedProduct {
    private ObjectId productId;
    private String productName;
    private int quantity;
    private String orderDate;
}