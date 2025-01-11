package com.example.restaurantreservationaa.domain.dto.beverage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeverageRegistrationDto {
    private String name;
    private String description;
    private double price;
    private String category;
}