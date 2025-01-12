package org.example.noSQL;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
public class noSQLClient {

    private ObjectId id;
    private String username;
    private String email;
    private String password;
    private String role;


    private List<noSQLOpinion> opinions;
    private List<noSQLOrderedProduct> orderedProducts;

    public noSQLClient(ObjectId id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
