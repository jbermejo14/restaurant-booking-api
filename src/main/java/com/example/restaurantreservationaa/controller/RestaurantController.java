package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Restaurant;
import com.example.restaurantreservationaa.exception.RestaurantNotFoundException;
import com.example.restaurantreservationaa.service.RestaurantService;
import com.example.restaurantreservationaa.domain.dto.RestaurantOutDto;
import com.example.restaurantreservationaa.domain.dto.RestaurantRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantOutDto>> getAll(@RequestParam(value = "name", defaultValue = "") String name,
                                                  @RequestParam(value = "address", defaultValue = "") String address)  {
        List<RestaurantOutDto> cars = restaurantService.getAll(name, address);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/restaurants/:restaurantId")
    public ResponseEntity<Restaurant> getRestaurant(long restaurantId)  throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantService.get(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantOutDto> addRestaurant(@RequestBody RestaurantRegistrationDto restaurant) {
        RestaurantOutDto newRestaurant = restaurantService.add(restaurant);
        return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/restaurant/:restaurantId")
    public ResponseEntity<Void> removeRestaurant(long restaurantId) throws RestaurantNotFoundException {
        restaurantService.remove(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleRestaurantNotFoundException(RestaurantNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
