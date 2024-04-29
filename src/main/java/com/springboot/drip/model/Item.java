package com.springboot.drip.model;

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
@Table(name = "order_items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(nullable = false)
    private int quantity;
    @Column(scale = 2)
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
