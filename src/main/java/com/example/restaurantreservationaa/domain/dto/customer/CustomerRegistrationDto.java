package com.example.restaurantreservationaa.domain.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDto {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
    private Date dateJoined;
}
