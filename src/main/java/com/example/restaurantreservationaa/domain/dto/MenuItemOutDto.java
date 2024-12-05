package com.example.restaurantreservationaa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemOutDto {
    private long id;
    private String name;
    private String description;
    private Float price;
    private String category;
    private Boolean isVegetarian;
}