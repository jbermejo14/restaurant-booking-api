package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.service.OrderService;
import com.example.restaurantreservationaa.repository.OrderRepository;
import com.example.restaurantreservationaa.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/orders/:orderId")
    public ResponseEntity<Order> getOrder(long customerId)  throws OrderNotFoundException {
        Order order = orderService.get(customerId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.add(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/order/:orderId")
    public ResponseEntity<Void> removeOrder(long orderId) throws OrderNotFoundException {
        orderService.remove(orderId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleOrderNotFoundException(OrderNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
