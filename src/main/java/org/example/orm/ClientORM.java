package org.example.orm;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "ClientORM")
@Table(name = "client")
public class ClientORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<OrderedProductORM> orderedProductORMS;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<OpinionORM> opinionORMS;


}
