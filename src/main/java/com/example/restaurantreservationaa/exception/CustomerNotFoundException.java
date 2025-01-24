package com.example.restaurantreservationaa.exception;

public class CustomerNotFoundException extends Throwable {

    public CustomerNotFoundException() {
        super("The customer does not exist");
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
