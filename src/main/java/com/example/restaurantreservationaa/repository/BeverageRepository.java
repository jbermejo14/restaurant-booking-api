package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Beverage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BeverageRepository extends CrudRepository<Beverage, Long> {
    List<Beverage> findAll();
    List<Beverage> findByName(String name);
    List<Beverage> findByPrice(float price);
}
