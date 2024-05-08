package com.springboot.drip.service;

import com.springboot.drip.model.Order;

import java.util.List;


public interface OrderService {
    Order addOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Long orderId);
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
}
