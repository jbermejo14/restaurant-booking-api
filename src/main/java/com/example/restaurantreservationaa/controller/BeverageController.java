package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.exception.BeverageNotFoundException;
import com.example.restaurantreservationaa.service.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beverages")
public class BeverageController {

    @Autowired
    private BeverageService beverageService;

    @GetMapping
    public ResponseEntity<List<Beverage>> getAll() {
        return new ResponseEntity<>(beverageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{beverageId}")
    public ResponseEntity<Beverage> getBeverage(long beverageId)  throws BeverageNotFoundException {
        Beverage beverage = beverageService.get(beverageId);
        return new ResponseEntity<>(beverage, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Beverage> addBeverage(@RequestBody Beverage beverage) {
        return new ResponseEntity<>(beverageService.add(beverage), HttpStatus.CREATED);
    }

    @DeleteMapping("/{beverageId}")
    public ResponseEntity<Void> removeMenuItem(long beverageId) throws BeverageNotFoundException{
        beverageService.remove(beverageId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleBeverageNotFoundException(BeverageNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
