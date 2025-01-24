package com.example.restaurantreservationaa.exception;

public class BeverageNotFoundException extends Throwable {

    public BeverageNotFoundException() {
        super("The beverage does not exist");
    }

    public BeverageNotFoundException(String message) {
        super(message);
    }
}
