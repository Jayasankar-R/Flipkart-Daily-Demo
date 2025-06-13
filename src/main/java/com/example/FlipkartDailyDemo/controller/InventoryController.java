package com.example.FlipkartDailyDemo.controller;

import com.example.FlipkartDailyDemo.dtos.AddItemRequestDTO;
import com.example.FlipkartDailyDemo.dtos.AddStockRequestDTO;
import com.example.FlipkartDailyDemo.dtos.SearchRequestDTO;
import com.example.FlipkartDailyDemo.entities.Item;
import com.example.FlipkartDailyDemo.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping("/add-item")
    public ResponseEntity<?> addItem(
            @RequestParam String brand,
            @RequestParam String category,
            @RequestParam int price
    ) {
        // You can still use the DTO in service layer if needed
        AddItemRequestDTO dto = new AddItemRequestDTO();
        dto.setBrand(brand);
        dto.setCategory(category);
        dto.setPrice(price);

        service.addItem(dto); // send DTO to service
        return ResponseEntity.ok("Item Added");
    }


    @PostMapping("/add-stock")
    public ResponseEntity<?> addStock(@RequestParam String brand, @RequestParam String category, @RequestParam int qty) {
        AddStockRequestDTO dto= new AddStockRequestDTO();
        dto.setBrand(brand);
        dto.setCategory(category);
        dto.setQty(qty);
        boolean updated = service.addInventory(dto);
        return updated ? ResponseEntity.ok("Inventory Updated") : ResponseEntity.badRequest().body("Item not found");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> search(SearchRequestDTO dto) {
        return ResponseEntity.ok(service.search(dto));
    }


    @GetMapping("/view")
    public ResponseEntity<Map<String, Map<String, Integer>>> viewInventory() {
        return ResponseEntity.ok(service.getInventoryView());
    }
}

