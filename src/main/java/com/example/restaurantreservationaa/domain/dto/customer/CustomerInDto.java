package com.example.restaurantreservationaa.domain.dto.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInDto {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
}