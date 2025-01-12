package org.example.orm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "ManufacturerORM")
@Table(name = "manufacturer")
public class ManufacturerORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<ProductORM> productORMS;


}