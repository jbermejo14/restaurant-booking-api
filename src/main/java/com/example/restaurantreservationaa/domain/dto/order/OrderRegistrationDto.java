package com.example.restaurantreservationaa.domain.dto.order;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRegistrationDto {
    private List<MenuItem> menuItems;
    private List<Beverage> beverages;
    private String status;
    private Date orderDate;
    private double totalPrice;
}
