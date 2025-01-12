package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class noSQLOpinion {
    private ObjectId productId;
    private int rating;
    private String content;

    public noSQLOpinion(ObjectId productId, int rating, String content) {
        this.productId = productId;
        this.rating = rating;
        this.content = content;
    }

    public noSQLOpinion(){}

}
