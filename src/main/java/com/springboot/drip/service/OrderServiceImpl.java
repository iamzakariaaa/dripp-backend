package com.springboot.drip.service;

import com.springboot.drip.dto.ItemDTO;
import com.springboot.drip.dto.OrderDTO;
import com.springboot.drip.model.Item;
import com.springboot.drip.model.Order;
import com.springboot.drip.repository.ItemRepository;
import com.springboot.drip.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    @Override
    public Order addOrder(Order order) {
        order = orderRepository.save(order);
        for (Item item : order.getItems()) {
            item.setOrder(order);
            itemRepository.save(item);
        }

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

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setPhoneNumber(order.getPhoneNumber());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCustomerId(order.getCustomer().getId());
        // Convert items to ItemDTOs
        List<ItemDTO> itemDTOs = order.getItems().stream()
                .map(this::convertItemToDTO)
                .collect(Collectors.toList());
        orderDTO.setItems(itemDTOs);

        return orderDTO;
    }

    private ItemDTO convertItemToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getProduct().getName());
        itemDTO.setQuantity(item.getQuantity());
        itemDTO.setAmount(item.getAmount());
        return itemDTO;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

