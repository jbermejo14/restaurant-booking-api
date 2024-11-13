package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.exception.OrderNotFoundException;
import com.example.restaurantreservationaa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        List<Order> allOrders = orderRepository.findAll();
        return allOrders;
    }

    public Order get(long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order add(Order order) {
        return orderRepository.save(order);
    }

    public void remove(long orderId) throws OrderNotFoundException {
        orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderRepository.deleteById(orderId);
    }
}
