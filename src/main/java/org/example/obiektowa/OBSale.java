package org.example.obiektowa;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class OBSale {
    private ObjectId id;
    private String name;
    private String startDate;
    private String endDate;
}
