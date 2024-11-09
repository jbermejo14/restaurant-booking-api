package com.example.restaurantreservationaa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="menuitems")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Float price;
    @Column
    private String category;
    @Column
    private Boolean isVegetarian;

//    @ManyToMany(mappedBy = "menuitem")
//    private List<Order> order;
}
