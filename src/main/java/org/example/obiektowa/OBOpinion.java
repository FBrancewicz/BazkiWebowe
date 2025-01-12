package org.example.obiektowa;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class OBOpinion {
    private ObjectId productId;
    private String productName;
    private int rating;
    private String content;
}