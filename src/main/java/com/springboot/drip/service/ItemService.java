package com.springboot.drip.service;

import com.springboot.drip.dto.ItemDto;
import com.springboot.drip.model.Item;

import java.util.List;

import java.util.List;

public interface ItemService {
    ItemDto addItem(ItemDto itemDTO);
    ItemDto updateItem(ItemDto itemDTO);
    void deleteItem(Long itemId);
    List<ItemDto> getAllItems();
    ItemDto getItemById(Long itemId);
}
