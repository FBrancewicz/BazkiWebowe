package org.example.obiektowa;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class OBManufacturer {
    private ObjectId id;
    private String name;
}
