package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BeverageRepository extends CrudRepository<Beverage, Long>, JpaRepository<Beverage, Long>, JpaSpecificationExecutor<Beverage> {
    List<Beverage> findAll();
    List<Beverage> findByName(String name);
    List<Beverage> findByDescription(String description);
    List<Beverage> findByNameAndDescription(String name, String description);
    List<Beverage> findByNameAndDescriptionAndCategory(String name, String description, String category);
}
