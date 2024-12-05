package com.example.restaurantreservationaa.domain.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantInDto {
    private String name;
    private String address;
    private String phone;

}