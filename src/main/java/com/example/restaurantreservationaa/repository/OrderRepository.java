package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findByOrderDate(Date orderDate);
    List<Order> findByTotalPrice(int totalPrice);
}
