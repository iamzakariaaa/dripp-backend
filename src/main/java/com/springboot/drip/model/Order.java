package com.springboot.drip.model;

import com.springboot.drip.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shipping_address", nullable = false)
    private String address;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(name = "order_date",nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "total_amount",nullable = false)
    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private User customer;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

}
