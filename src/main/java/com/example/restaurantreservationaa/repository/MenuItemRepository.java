package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAll();
    List<MenuItem> findByName(String name);
    List<MenuItem> findByPrice(float price);
}
