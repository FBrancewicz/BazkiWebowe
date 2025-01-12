package org.example.noSQL;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class noSQLOpinion {
    private ObjectId productId; // ID produktu, którego dotyczy opinia
    private int rating; // Ocena produktu
    private String content; // Treść opinii

    public noSQLOpinion(ObjectId productId, int rating, String content) {
        this.productId = productId;
        this.rating = rating;
        this.content = content;
    }

    public noSQLOpinion(){}

}
