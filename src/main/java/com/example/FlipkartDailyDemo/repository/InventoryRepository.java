package com.example.FlipkartDailyDemo.repository;

import com.example.FlipkartDailyDemo.entities.Item;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InventoryRepository {
    private final Map<String, Item> inventory = new ConcurrentHashMap<>();

    private String getKey(String brand, String category) {
        return brand + "#" + category;
    }

    public void saveItem(Item item) {
        inventory.putIfAbsent(getKey(item.getBrand(), item.getCategory()), item);
    }

    public Item find(String brand, String category) {
        return inventory.get(getKey(brand, category));
    }

    public Collection<Item> findAll() {
        return inventory.values();
    }
}


