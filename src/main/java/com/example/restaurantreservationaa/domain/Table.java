package com.example.restaurantreservationaa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int tableNum;
    @Column
    private int seatingCapacity;
    @Column
    private boolean isAvailable;
    @Column
    private String location;
    @Column
    private String features;

    @OneToMany(mappedBy = "table")
    private List<Reservation> reservation;
}