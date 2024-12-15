package com.example.restaurantreservationaa.domain.dto.order;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.MenuItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInDto {
    private List<MenuItem> menuItems;
    private List<Beverage> beverages;
    private Date orderDate;
    private double totalPrice;
}
