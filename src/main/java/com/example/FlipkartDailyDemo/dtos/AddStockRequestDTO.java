package com.example.FlipkartDailyDemo.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddStockRequestDTO {
    @NotBlank
    private String brand;

    @NotBlank
    private String category;

    @Min(1)
    private int qty;


}

