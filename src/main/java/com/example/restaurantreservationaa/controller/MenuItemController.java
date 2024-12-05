package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.exception.MenuItemNotFoundException;
import com.example.restaurantreservationaa.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuitems")

public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAll() {
        return new ResponseEntity<>(menuItemService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{menuitemId}")
    public ResponseEntity<MenuItem> getMenuItem(long customerId)  throws MenuItemNotFoundException {
        MenuItem menuItem = menuItemService.get(customerId);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuitem) {
        return new ResponseEntity<>(menuItemService.add(menuitem), HttpStatus.CREATED);
    }

    @DeleteMapping("/{menuitemId}")
    public ResponseEntity<Void> removeMenuItem(long menuitemId) throws MenuItemNotFoundException{
        menuItemService.remove(menuitemId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleMenuItemNotFoundException(MenuItemNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
