package com.example.restaurantreservationaa.domain.dto.menuitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRegistrationDto {
    private String name;
    private String description;
    private double price;
    private String category;
    private Boolean isVegetarian;
}