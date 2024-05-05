package com.springboot.drip.service;

import com.springboot.drip.dto.OrderDto;
import com.springboot.drip.model.Item;
import com.springboot.drip.model.Order;

import java.util.List;

import java.util.List;

public interface OrderService {
    OrderDto addOrder(OrderDto orderDto);
    OrderDto updateOrder(OrderDto orderDto);
    void deleteOrder(Long orderId);
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long orderId);
}
