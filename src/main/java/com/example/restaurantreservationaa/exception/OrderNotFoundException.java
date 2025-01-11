package com.example.restaurantreservationaa.exception;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException() {
        super("The order does not exist");
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
