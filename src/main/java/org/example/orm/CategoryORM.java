package org.example.orm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "CategoryORM")
@Table(name = "Category")
public class CategoryORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Wspiera AUTO_INCREMENT
    private Integer id; // Zmienione z String na Integer

    private String name;
    private String description;

}
