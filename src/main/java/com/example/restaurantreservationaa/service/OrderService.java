package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.domain.dto.order.OrderInDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderOutDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderRegistrationDto;
import com.example.restaurantreservationaa.exception.OrderNotFoundException;
import com.example.restaurantreservationaa.repository.OrderRepository;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<OrderOutDto> getAll(double totalPrice, Date orderDate) {
        List<Order> orderList;

        if (totalPrice == 0.0 && orderDate == null) {
            orderList = orderRepository.findAll();
        } else if (totalPrice == 0.0) {
            orderList = orderRepository.findByOrderDate(orderDate);
        } else if (orderDate == null) {
            orderList = orderRepository.findByTotalPrice(totalPrice);
        } else {
            orderList = orderRepository.findByOrderDateAndTotalPrice(orderDate, totalPrice);
        }

        return modelMapper.map(orderList, new TypeToken<List<OrderOutDto>>() {
        }.getType());
    }

    public Order get(long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> getOrdersByFilter(Date orderDate, Double totalPrice, String status) {
        return orderRepository.findAll((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            // Check for orderDate filter
            if (orderDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("orderDate"), orderDate));
            }
            // Check for totalPrice filter
            if (totalPrice != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("totalPrice"), totalPrice));
            }
            // Check for status filter
            if (status != null && !status.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("status"), "%" + status + "%"));
            }

            return predicate;
        });
    }

    public OrderOutDto add(OrderRegistrationDto orderInDto) {
        Order order = modelMapper.map(orderInDto, Order.class);
        Order newOrder = orderRepository.save(order);
        return modelMapper.map(newOrder, OrderOutDto.class);
    }

    public OrderOutDto modify(long orderId, OrderInDto orderInDto) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        modelMapper.map(orderInDto, order);
        orderRepository.save(order);

        return modelMapper.map(order, OrderOutDto.class);
    }

    public void remove(long orderId) throws OrderNotFoundException {
        orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderRepository.deleteById(orderId);
    }
}
