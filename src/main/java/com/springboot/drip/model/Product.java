package com.springboot.drip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.drip.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(scale = 2)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "units_in_stock",nullable = true)
    private int units;
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private String image;
    @OneToOne(mappedBy = "product" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private Item item;
}
