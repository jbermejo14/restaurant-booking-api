package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemRegistrationDto;
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
    public ResponseEntity<List<MenuItemOutDto>> getAll(@RequestParam(value = "name", defaultValue = "") String name,
                                                @RequestParam(value = "description", defaultValue = "") String description,
                                                       @RequestParam(value = "price", defaultValue = "0.0") double price)  {
        List<MenuItemOutDto> menuItems = menuItemService.getAll(name, description, price);
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/{menuitemId}")
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable long menuitemId)  throws MenuItemNotFoundException {
        MenuItem menuItem = menuItemService.get(menuitemId);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuItemOutDto> addMenuItem(@RequestBody MenuItemRegistrationDto menuItem) {
        MenuItemOutDto newMenuItem = menuItemService.add(menuItem);
        return new ResponseEntity<>(newMenuItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{menuitemId}")
    public ResponseEntity<Void> removeMenuItem(@PathVariable long menuitemId) throws MenuItemNotFoundException{
        menuItemService.remove(menuitemId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleMenuItemNotFoundException(MenuItemNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
