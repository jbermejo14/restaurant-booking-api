package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.domain.dto.order.OrderOutDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderRegistrationDto;import com.example.restaurantreservationaa.exception.OrderNotFoundException;
import com.example.restaurantreservationaa.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<OrderOutDto> getAll(double totalPrice, int quantity) {
        List<Order> orderList;

        if (totalPrice == 0.0 && quantity == 0) {
            orderList = orderRepository.findAll();
        } else if (totalPrice == 0.0) {
            orderList = orderRepository.findByQuantity(quantity);
        } else if (quantity == 0) {
            orderList = orderRepository.findByTotalPrice(totalPrice);
        } else {
            orderList = orderRepository.findByQuantityAndTotalPrice(quantity, totalPrice);
        }

        return modelMapper.map(orderList, new TypeToken<List<OrderOutDto>>() {
        }.getType());
    }

    public Order get(long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public OrderOutDto add(OrderRegistrationDto orderInDto) {
        Order order = modelMapper.map(orderInDto, Order.class);
        Order newOrder = orderRepository.save(order);
        return modelMapper.map(newOrder, OrderOutDto.class);
    }

    public void remove(long orderId) throws OrderNotFoundException {
        orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderRepository.deleteById(orderId);
    }
}
