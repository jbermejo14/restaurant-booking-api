package com.example.restaurantreservationaa.domain.dto.menuitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRegistrationDto {
    private long id;
    private String name;
    private String description;
    private Float price;
    private String category;
    private Boolean isVegetarian;
}