package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Restaurant;
import com.example.restaurantreservationaa.domain.dto.ErrorResponse;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemInDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantInDto;
import com.example.restaurantreservationaa.exception.BeverageNotFoundException;
import com.example.restaurantreservationaa.exception.RestaurantNotFoundException;
import com.example.restaurantreservationaa.service.RestaurantService;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantOutDto;
import com.example.restaurantreservationaa.domain.dto.restaurant.RestaurantRegistrationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public ResponseEntity<List<RestaurantOutDto>> getAll(@RequestParam(value = "name", defaultValue = "") String name,
                                                         @RequestParam(value = "address", defaultValue = "") String address)  {
        List<RestaurantOutDto> restaurants = restaurantService.getAll(name, address);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable long restaurantId) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantService.get(restaurantId);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> getRestaurantsByFilter(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "address", required = false) String address ,
            @RequestParam(value = "phone", required = false) String phone) {

        List<Restaurant> restaurants = restaurantService.getRestaurantsByFilter(name, address, phone);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestaurantOutDto> addRestaurant(@RequestBody RestaurantRegistrationDto restaurant) {
        RestaurantOutDto newRestaurant = restaurantService.add(restaurant);
        return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> removeRestaurant(@PathVariable long restaurantId) throws RestaurantNotFoundException {
        restaurantService.remove(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantOutDto> modifyRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantInDto restaurant) throws RestaurantNotFoundException {
        RestaurantOutDto modifiedRestaurant = restaurantService.modify(restaurantId, restaurant);
        return new ResponseEntity<>(modifiedRestaurant, HttpStatus.OK);
    }

    @PatchMapping("/{restaurantId}")
    public ResponseEntity<RestaurantOutDto> partialUpdateRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantInDto restaurantInDto) throws RestaurantNotFoundException {
        RestaurantOutDto modifiedRestaurant = restaurantService.partialUpdate(restaurantId, restaurantInDto);
        return new ResponseEntity<>(modifiedRestaurant, HttpStatus.OK);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse error = ErrorResponse.generalError(500, "Internal Server Error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
