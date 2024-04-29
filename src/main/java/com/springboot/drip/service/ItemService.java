package com.springboot.drip.service;

import com.springboot.drip.model.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    Item updateItem(Item item);
    void deleteItem(Long itemId);
    List<Item> getAllItems();
    Item getItemById(Long itemId);
}
