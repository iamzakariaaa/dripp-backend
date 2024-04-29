package com.springboot.drip.service;

import com.springboot.drip.enums.Status;
import com.springboot.drip.model.Item;
import com.springboot.drip.model.Order;
import com.springboot.drip.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        order.setStatus(Status.PENDING);
        BigDecimal totalAmount = calculateTotalAmount(order);
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }
    @Override
    public void cancelOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(Status.CANCELED);
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found");
        }
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
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
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElse(null);
    }

    private BigDecimal calculateTotalAmount(Order order) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Item item : order.getItems()) {
            totalAmount = totalAmount.add(item.getAmount());
        }
        return totalAmount;
    }
}

