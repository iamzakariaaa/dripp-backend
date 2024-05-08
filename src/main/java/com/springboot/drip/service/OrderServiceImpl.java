package com.springboot.drip.service;

import com.springboot.drip.model.Item;
import com.springboot.drip.model.Order;
import com.springboot.drip.repository.ItemRepository;
import com.springboot.drip.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        Order existingOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setAddress(order.getAddress());
        existingOrder.setPhoneNumber(order.getPhoneNumber());
        order = orderRepository.save(existingOrder);
        return order;
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

