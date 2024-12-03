package com.example.restaurantreservationaa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRegistrationDto {
    private long id;
    private String name;
    private String address;
    private String phone;
    private double latitude;
    private double longitude;
}
