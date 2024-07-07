package com.springboot.drip.service;

import com.springboot.drip.dto.OrderDTO;
import com.springboot.drip.model.Order;

import java.util.List;


public interface OrderService {
    Order addOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Long orderId);
    List<OrderDTO> getAllOrders();
    Order getOrderById(Long orderId);
}
