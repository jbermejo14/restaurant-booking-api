package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long>, JpaRepository<MenuItem, Long>, JpaSpecificationExecutor<MenuItem> {
    List<MenuItem> findAll();
    List<MenuItem> findByName(String name);
    List<MenuItem> findByPrice(double price);
    List<MenuItem> findByDescription(String description);
    List<MenuItem> findByNameAndDescription(String name, String description);
    List<Beverage> findByNameAndDescriptionAndCategory(String name, String description, String category);
}
