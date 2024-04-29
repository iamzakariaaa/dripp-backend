package com.springboot.drip.service;

import com.springboot.drip.model.Item;
import com.springboot.drip.model.Item;
import com.springboot.drip.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
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
    public Item getItemById(Long itemId){
        Optional<Item> item = itemRepository.findById(itemId);
        return item.get();
    }
}

