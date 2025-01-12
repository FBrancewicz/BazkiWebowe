package org.example.noSQL;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
public class noSQLClient {

    private ObjectId id; // ID klienta
    private String username; // Nazwa użytkownika
    private String email; // Email klienta
    private String password; // Hasło klienta
    private String role; // Rola klienta (np. USER, ADMIN)

    // Opinie klienta
    private List<noSQLOpinion> opinions; // Lista opinii (np. dla produktów)
    private List<noSQLOrderedProduct> orderedProducts; // Lista zamówionych produktów

    public noSQLClient(ObjectId id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
