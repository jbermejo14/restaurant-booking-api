package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Order;
import com.example.restaurantreservationaa.domain.dto.ErrorResponse;
import com.example.restaurantreservationaa.domain.dto.order.OrderInDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderOutDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderRegistrationDto;
import com.example.restaurantreservationaa.service.OrderService;
import com.example.restaurantreservationaa.exception.OrderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public ResponseEntity<List<OrderOutDto>> getAll(@RequestParam(value = "totalPrice", required = false, defaultValue = "0.0") Float totalPrice,
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

    @GetMapping("/search")
    public ResponseEntity<List<Order>> getOrderByFilter(
            @RequestParam(value = "orderDate", required = false) Date orderDate,
            @RequestParam(value = "totalPrice", required = false) Float totalPrice ,
            @RequestParam(value = "status", required = false) String status) {

        List<Order> orders = orderService.getOrdersByFilter(orderDate, totalPrice, status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderOutDto> addOrder(@RequestBody  OrderRegistrationDto order) {
        OrderOutDto newOrder = orderService.add(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderOutDto> modifyOrder(@PathVariable long orderId, @RequestBody OrderInDto order) throws OrderNotFoundException {
        OrderOutDto modifiedOrder = orderService.modify(orderId, order);
        return new ResponseEntity<>(modifiedOrder, HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderOutDto> partialUpdateOrder(@PathVariable long orderId, @RequestBody OrderInDto orderInDto) throws OrderNotFoundException {
        OrderOutDto modifiedOrder = orderService.partialUpdate(orderId, orderInDto);
        return new ResponseEntity<>(modifiedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> removeOrder(@PathVariable long orderId) throws OrderNotFoundException {
        orderService.remove(orderId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException exception) {
        ErrorResponse error = ErrorResponse.generalError(404, exception.getMessage());
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        logger.error(exception.getMessage(), exception);

        return new ResponseEntity<>(ErrorResponse.validationError(errors), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
//        ErrorResponse error = ErrorResponse.generalError(500, "Internal Server Error");
//        logger.error(exception.getMessage(), exception);
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
