package com.springboot.drip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {
    private Long id;
    private String name;
    private int quantity;
    private BigDecimal amount;
}
