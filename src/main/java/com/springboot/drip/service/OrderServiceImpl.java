package com.springboot.drip.service;

import com.springboot.drip.dto.ItemDto;
import com.springboot.drip.dto.OrderDto;
import com.springboot.drip.model.Item;
import com.springboot.drip.model.Order;
import com.springboot.drip.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = mapToEntity(orderDto);
        order = orderRepository.save(order);
        return mapToDto(order);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        // Update order properties
        order.setAddress(orderDto.getAddress());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        // Update other properties as needed
        order = orderRepository.save(order);
        return mapToDto(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToDto(order);
    }

    // Helper method to map entity to DTO
    private OrderDto mapToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .address(order.getAddress())
                .phoneNumber(order.getPhoneNumber())
                .createdAt(order.getCreatedAt())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .customerId(order.getCustomer().getId())
                .items(order.getItems().stream().map(this::mapItemToDto).collect(Collectors.toList()))
                .build();
    }

    // Helper method to map Item entity to ItemDto
    private ItemDto mapItemToDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .amount(item.getAmount())
                .build();
    }

    // Helper method to map DTO to entity
    private Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setAddress(orderDto.getAddress());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        // Map other properties as needed
        return order;
    }
}


