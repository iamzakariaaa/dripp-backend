package com.springboot.drip.controller;

import com.springboot.drip.model.Item;
import com.springboot.drip.model.Product;
import com.springboot.drip.service.ItemService;
import com.springboot.drip.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addToCart/{id}")
    public ResponseEntity<Item> addItem(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(1);
        BigDecimal amount = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
        item.setAmount(amount);
        Item addedItem = itemService.addItem(item);
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        BigDecimal amount = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
        item.setAmount(amount);
        item.setId(id);
        Item updatedItem = itemService.updateItem(item);
        if (updatedItem != null) {
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
