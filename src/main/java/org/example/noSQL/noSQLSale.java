package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class noSQLSale {
    private ObjectId id; // ID promocji
    private String name; // Nazwa promocji
    private String startDate; // Data rozpoczęcia promocji (jako String)
    private String endDate; // Data zakończenia promocji (jako String)

    public noSQLSale(ObjectId id, String name, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public noSQLSale(){

    }
}
