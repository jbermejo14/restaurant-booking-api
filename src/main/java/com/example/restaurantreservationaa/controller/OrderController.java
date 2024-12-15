package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.domain.dto.order.OrderOutDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderRegistrationDto;
import com.example.restaurantreservationaa.service.OrderService;
import com.example.restaurantreservationaa.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderOutDto>> getAll(@RequestParam(value = "totalPrice", required = false, defaultValue = "0.0") double totalPrice,
                                                    @RequestParam(value = "orderDate", required = false)
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate) {

        List<OrderOutDto> orders = orderService.getAll(totalPrice, orderDate);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable long orderId)  throws OrderNotFoundException {
        Order order = orderService.get(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderOutDto> addOrder(@RequestBody  OrderRegistrationDto order) {
        OrderOutDto newOrder = orderService.add(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> removeOrder(@PathVariable long orderId) throws OrderNotFoundException {
        orderService.remove(orderId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleOrderNotFoundException(OrderNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
