package com.example.restaurantreservationaa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private Date orderDate;
    @ManyToMany
    @JoinColumn(name = "menuitem_id")
    private MenuItem menuitem;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    @Column
    private int quantity;
    @Column
    private int totalPrice;
}
