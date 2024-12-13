package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findByQuantity(int quantity);
    List<Order> findByTotalPrice(double totalPrice);
    List<Order> findByQuantityAndTotalPrice(int quantity, double totalPrice);
}
