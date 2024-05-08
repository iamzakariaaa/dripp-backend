package com.springboot.drip.service;


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

    @Override
    public Item addItem(Item item) {
        item = itemRepository.save(item);
        return item;
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}

