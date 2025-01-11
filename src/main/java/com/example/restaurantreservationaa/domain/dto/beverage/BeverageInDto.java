package com.example.restaurantreservationaa.domain.dto.beverage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeverageInDto {
    private String name;
    private String description;
    private Float price;
    private String category;
}