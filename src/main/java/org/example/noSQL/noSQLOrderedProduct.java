package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class noSQLOrderedProduct {
    private ObjectId productId;
    private int quantity;
    private String orderDate;

    public noSQLOrderedProduct(ObjectId productId, int quantity, String orderDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }
}
