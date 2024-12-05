package com.example.restaurantreservationaa.domain.dto;

import com.example.restaurantreservationaa.domain.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemInDto {
    private String name;
    private String description;
    private Float price;
    private String category;
    private Boolean isVegetarian;
}