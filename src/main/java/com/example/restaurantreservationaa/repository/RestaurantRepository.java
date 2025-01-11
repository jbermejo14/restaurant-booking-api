package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>, JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {
    List<Restaurant> findAll();
    List<Restaurant> findByName(String name);
    List<Restaurant> findByAddress(String address);
    List<Restaurant> findByNameAndAddress(String name, String address);
    List<Restaurant> findByNameAndAddressAndPhone(String name, String address, String phone);
}
