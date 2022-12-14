package com.cydeo.lab07ormqueries.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product extends BaseEntity {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private Integer remaining_quantity;


    @ManyToMany
@JoinTable(name="product_category_rel",
        joinColumns = @JoinColumn(name = "p_id"),
        inverseJoinColumns = @JoinColumn(name = "c_id"))

    private List<Category> category;
}
