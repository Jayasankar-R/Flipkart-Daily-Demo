package com.example.FlipkartDailyDemo.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {

    private String brand;
    private String category;
    private int price;
    private int quantity;


    public Item(String brand, String category, int price, int quantity) {
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}

