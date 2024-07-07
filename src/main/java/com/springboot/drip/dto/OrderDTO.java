package com.springboot.drip.dto;

import com.springboot.drip.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private String address;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private Status status;
    private Long customerId;
    private List<ItemDTO> items;
}
