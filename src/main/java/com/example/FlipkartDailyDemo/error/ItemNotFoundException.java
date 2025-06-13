package com.example.FlipkartDailyDemo.error;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String msg) { super(msg); }
}
