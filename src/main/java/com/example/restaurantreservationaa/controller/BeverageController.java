package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.dto.ErrorResponse;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageInDto;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageOutDto;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageRegistrationDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerInDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerOutDto;
import com.example.restaurantreservationaa.exception.BeverageNotFoundException;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.service.BeverageService;
import jakarta.validation.Valid;
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
@RequestMapping("/beverages")
public class BeverageController {

    @Autowired
    private BeverageService beverageService;
    private final Logger logger = LoggerFactory.getLogger(BeverageController.class);

    @GetMapping
    public ResponseEntity<List<BeverageOutDto>> getAll(@RequestParam(value = "name", defaultValue = "") String name,
                                                       @RequestParam(value = "description", defaultValue = "") String description)  {
        List<BeverageOutDto> beverages = beverageService.getAll(name, description);
        return new ResponseEntity<>(beverages, HttpStatus.OK);
    }

    @GetMapping("/{beverageId}")
    public ResponseEntity<Beverage> getBeverage(@PathVariable long beverageId)  throws BeverageNotFoundException {
        Beverage beverage = beverageService.get(beverageId);
        return new ResponseEntity<>(beverage, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Beverage>> getBeverageByFilter(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description ,
            @RequestParam(value = "category", required = false) String category) {

        List<Beverage> beverages = beverageService.getBeveragesByFilter(name, description, category);
        return new ResponseEntity<>(beverages, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeverageOutDto> addBeverage(@RequestBody @Valid BeverageRegistrationDto beverage) {
        BeverageOutDto newBeverage = beverageService.add(beverage);
        return new ResponseEntity<>(newBeverage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{beverageId}")
    public ResponseEntity<Void> removeBeverage(@PathVariable long beverageId) throws BeverageNotFoundException{
        beverageService.remove(beverageId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{beverageId}")
    public ResponseEntity<BeverageOutDto> modifyBeverage(@PathVariable @Valid Long beverageId, @RequestBody BeverageInDto beverage) throws BeverageNotFoundException {
        BeverageOutDto modifiedBeverage = beverageService.modify(beverageId, beverage);
        return new ResponseEntity<>(modifiedBeverage, HttpStatus.OK);
    }

    @PatchMapping("/{beverageId}")
    public ResponseEntity<BeverageOutDto> partialUpdateBeverage(@PathVariable Long beverageId, @RequestBody BeverageInDto beverageInDto) throws BeverageNotFoundException {
        BeverageOutDto updatedBeverage = beverageService.partialUpdate(beverageId, beverageInDto);
        return new ResponseEntity<>(updatedBeverage, HttpStatus.OK);
    }

    @ExceptionHandler(BeverageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBeverageNotFoundException(BeverageNotFoundException exception) {
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

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
//        ErrorResponse error = ErrorResponse.generalError(500, "Internal Server Error");
//        logger.error(exception.getMessage(), exception);
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
