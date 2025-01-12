package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class noSQLSale {
    private ObjectId id;
    private String name;
    private String startDate;
    private String endDate;

    public noSQLSale(ObjectId id, String name, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public noSQLSale(){

    }
}
