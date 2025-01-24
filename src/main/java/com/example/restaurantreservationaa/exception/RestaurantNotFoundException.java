package com.example.restaurantreservationaa.exception;

public class RestaurantNotFoundException extends Throwable {
    public RestaurantNotFoundException() {
        super("The Restaurant does not exist");
    }

    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
