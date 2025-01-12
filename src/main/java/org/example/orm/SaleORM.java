package org.example.orm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "SaleORM")
@Table(name = "sale")
public class SaleORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private java.util.Date startDate;

    @Temporal(TemporalType.DATE)
    private java.util.Date endDate;

    @OneToMany(mappedBy = "saleORM", cascade = CascadeType.ALL)
    private List<ProductORM> productORMS;


}
