package com.example.FlipkartDailyDemo.services;

import com.example.FlipkartDailyDemo.dtos.AddItemRequestDTO;
import com.example.FlipkartDailyDemo.dtos.AddStockRequestDTO;
import com.example.FlipkartDailyDemo.dtos.SearchRequestDTO;
import com.example.FlipkartDailyDemo.entities.Item;
import com.example.FlipkartDailyDemo.error.ItemNotFoundException;
import com.example.FlipkartDailyDemo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repo;

    public void addItem(AddItemRequestDTO req) {
        if (req.getPrice() < 0) throw new IllegalArgumentException("Price must be non-negative");
        repo.saveItem(new Item(req.getBrand(), req.getCategory(), req.getPrice(), 0));
    }

    public boolean addInventory(AddStockRequestDTO req) {
        Item item = repo.find(req.getBrand(), req.getCategory());
        if (item != null) {
            item.setQuantity(item.getQuantity() + req.getQty());
            return true;
        }
        return false;
    }

    public List<Item> search(SearchRequestDTO dto) {
        Map<String, List<String>> filters = new HashMap<>();
        if (dto.getBrand() != null) filters.put("brand", dto.getBrand());
        if (dto.getCategory() != null) filters.put("category", dto.getCategory());

        int[] priceRange = null;
        if (dto.getFromPrice() != null || dto.getToPrice() != null) {
            priceRange = new int[]{
                    dto.getFromPrice() == null ? -1 : dto.getFromPrice(),
                    dto.getToPrice() == null ? Integer.MAX_VALUE : dto.getToPrice()
            };
        }

        boolean asc = dto.getOrder().equalsIgnoreCase("asc");
        String sortBy = dto.getOrderBy();
        int from = -1, to = Integer.MAX_VALUE;

        if (priceRange != null) {
            from = priceRange[0];
            to = priceRange[1];
        }
        final int finalFrom = from;
        final int finalTo = to;
        return repo.findAll().stream()
                .filter(item -> {
                    if (filters.containsKey("brand") && !filters.get("brand").contains(item.getBrand())) return false;
                    if (filters.containsKey("category") && !filters.get("category").contains(item.getCategory())) return false;
                    if ((finalFrom != -1 && item.getPrice() < finalFrom) || (finalTo != -1 && item.getPrice() > finalTo))
                        return false;
                    return true;
                })
                .sorted((a, b) -> {
                    int cmp = 0;
                    switch (sortBy.toLowerCase()) {
                        case "price": cmp = Integer.compare(a.getPrice(), b.getPrice()); break;
                        case "itemqty": cmp = Integer.compare(a.getQuantity(), b.getQuantity()); break;
                    }
                    return asc ? cmp : -cmp;
                })
                .toList();
    }


    public Map<String, Map<String, Integer>> getInventoryView() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(
                        Item::getBrand,
                        TreeMap::new,
                        Collectors.toMap(
                                Item::getCategory,
                                Item::getQuantity,
                                (e1, e2) -> e1,
                                TreeMap::new
                        )
                ));
    }
}

