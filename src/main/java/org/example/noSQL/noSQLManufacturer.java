package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class noSQLManufacturer {
    private ObjectId id;
    private String name;

    public noSQLManufacturer(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }

    public noSQLManufacturer(){

    }
}
