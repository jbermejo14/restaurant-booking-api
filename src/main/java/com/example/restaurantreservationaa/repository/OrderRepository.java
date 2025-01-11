package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>, JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findAll();
    List<Order> findByOrderDate(Date orderDate);
    List<Order> findByTotalPrice(double totalPrice);
    List<Order> findByOrderDateAndTotalPrice(Date orderDate, double totalPrice);
    List<Order> findByOrderDateAndTotalPriceAndStatus(Date orderDate, double totalPrice, String status);
}
