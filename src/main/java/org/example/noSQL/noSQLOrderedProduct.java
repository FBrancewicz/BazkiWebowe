package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class noSQLOrderedProduct {
    private ObjectId productId; // ID zamówionego produktu
    private int quantity; // Ilość zamówionych produktów
    private String orderDate; // Data zamówienia (jako String)

    public noSQLOrderedProduct(ObjectId productId, int quantity, String orderDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }
}
