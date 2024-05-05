package com.springboot.drip.controller;

import com.springboot.drip.dto.ItemDto;
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
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> itemDtos = itemService.getAllItems();
        return new ResponseEntity<>(itemDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        ItemDto itemDto = itemService.getItemById(id);
        if (itemDto != null) {
            return new ResponseEntity<>(itemDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addToCart/{id}")
    public ResponseEntity<ItemDto> addItem(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setProductId(product.getId());
        itemDto.setQuantity(1);
        BigDecimal amount = product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
        itemDto.setAmount(amount);
        ItemDto addedItemDto = itemService.addItem(itemDto);
        return new ResponseEntity<>(addedItemDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        itemDto.setId(id); // Make sure the ID is set from path variable
        ItemDto updatedItemDto = itemService.updateItem(itemDto);
        if (updatedItemDto != null) {
            return new ResponseEntity<>(updatedItemDto, HttpStatus.OK);
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
