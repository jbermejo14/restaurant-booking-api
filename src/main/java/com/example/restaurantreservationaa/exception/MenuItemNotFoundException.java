package com.example.restaurantreservationaa.exception;

public class MenuItemNotFoundException extends Throwable {
    public MenuItemNotFoundException() {
        super("The Menu Item does not exist");
    }

    public MenuItemNotFoundException(String message) {
        super(message);
    }
}
