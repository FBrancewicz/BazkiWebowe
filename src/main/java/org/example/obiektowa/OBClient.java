package org.example.obiektowa;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
public class OBClient {
    private ObjectId id;
    private String username;
    private String email;
    private String password;
    private String role;

    private List<OBOrderedProduct> orderedProducts;
    private List<OBOpinion> opinions;
}