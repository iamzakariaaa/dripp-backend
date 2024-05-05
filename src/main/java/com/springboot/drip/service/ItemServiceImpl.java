package com.springboot.drip.service;

import com.springboot.drip.dto.ItemDto;
import com.springboot.drip.model.Item;
import com.springboot.drip.repository.ItemRepository;
import com.springboot.drip.repository.OrderRepository;
import com.springboot.drip.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        Item item = mapToEntity(itemDto);
        item = itemRepository.save(item);
        return mapToDto(item);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto) {
        Item item = itemRepository.findById(itemDto.getId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        // item.getProduct().setId(itemDto.getProductId()); // Assuming Product has setId method
        // item.getOrder().setId(itemDto.getOrderId()); // Assuming Order has setId method

        item.setQuantity(itemDto.getQuantity());
        item.setAmount(itemDto.getAmount());
        item = itemRepository.save(item);
        return mapToDto(item);
    }


    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return mapToDto(item);
    }

    // Helper method to map entity to DTO
    private ItemDto mapToDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .orderId(item.getOrder().getId())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .amount(item.getAmount())
                .build();
    }

    // Helper method to map DTO to entity
    private Item mapToEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        // Assuming you have Product and Order entities, you need to fetch product and order details here
        item.setProduct(productRepository.findById(itemDto.getProductId())
                                       .orElseThrow(() -> new RuntimeException("Product not found")));
        item.setOrder(orderRepository.findById(itemDto.getOrderId())
                                     .orElseThrow(() -> new RuntimeException("Order not found")));
        item.setQuantity(itemDto.getQuantity());
        item.setAmount(itemDto.getAmount());
        return item;
    }
}


