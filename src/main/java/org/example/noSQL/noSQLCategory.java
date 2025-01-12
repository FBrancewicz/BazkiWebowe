package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class noSQLCategory {
    private ObjectId id;
    private String name;
    private String description;

    public noSQLCategory(ObjectId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public noSQLCategory(){
    }
}
