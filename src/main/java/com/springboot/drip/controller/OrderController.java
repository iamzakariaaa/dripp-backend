package com.springboot.drip.controller;

import com.springboot.drip.dto.OrderDto;
import com.springboot.drip.model.Item;
import com.springboot.drip.model.Order;
import com.springboot.drip.model.User;
import com.springboot.drip.service.OrderService;
import com.springboot.drip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        // Assuming you have a method to retrieve the user by ID from the UserService
        User user = userService.getUserById(orderDto.getCustomerId());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderDto = orderService.addOrder(orderDto);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        // Ensure that the ID in the path variable matches the ID in the DTO
        if (!id.equals(orderDto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderDto = orderService.updateOrder(orderDto);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto orderDto = orderService.getOrderById(id);
        if (orderDto != null) {
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
