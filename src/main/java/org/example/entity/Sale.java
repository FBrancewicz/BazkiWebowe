package org.example.entity;

import jakarta.persistence.*;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Wspiera AUTO_INCREMENT
    private Integer id;

    private String name;
    private String startDate;
    private String endDate;

    @OneToMany(mappedBy = "sale")
    private List<Product> products;


}
