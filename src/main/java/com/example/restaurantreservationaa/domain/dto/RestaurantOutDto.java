package com.example.restaurantreservationaa.domain.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOutDto {
    private long id;
    private String name;
    private String address;
    private String phone;
}

