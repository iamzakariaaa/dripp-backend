package com.springboot.drip.controller;

import com.springboot.drip.model.Item;
import com.springboot.drip.model.Order;
import com.springboot.drip.model.User;
import com.springboot.drip.service.ItemService;
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
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        User user = userService.getUserById(order.getCustomer().getId());
        List<Item> items = order.getItems();
        order.setItems(items);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderService.addOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        if (!id.equals(order.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        order = orderService.updateOrder(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

