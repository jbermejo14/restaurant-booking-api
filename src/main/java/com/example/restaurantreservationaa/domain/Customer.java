package com.example.restaurantreservationaa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String password;
    @Column(name = "date_joined")
    private Date dateJoined;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference(value = "customer_reservations")
    private List<Reservation> reservations;
}
