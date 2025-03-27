package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.domain.dto.ErrorResponse;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerInDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerOutDto;
import com.example.restaurantreservationaa.domain.dto.customer.CustomerRegistrationDto;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public ResponseEntity<List<CustomerOutDto>> getAll(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "email", required = false) String email) {

        List<CustomerOutDto> customers = customerService.getAll(name, email);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long customerId) throws CustomerNotFoundException {
        Customer customer = customerService.get(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> getCustomerByFilter(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "role", required = false) String role) {

        List<Customer> customers = customerService.getCustomersByFilter(name, email, role);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerOutDto> addCustomer(@RequestBody @Valid CustomerRegistrationDto customer) {
        CustomerOutDto newCustomer = customerService.add(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerOutDto> modifyCustomer(@PathVariable @Valid long customerId, @RequestBody CustomerInDto customer) throws CustomerNotFoundException {
        CustomerOutDto modifiedCustomer = customerService.modify(customerId, customer);
        return new ResponseEntity<>(modifiedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> removeCustomer(@PathVariable long customerId) throws CustomerNotFoundException{
        customerService.remove(customerId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<CustomerOutDto> partialUpdateCustomer(@PathVariable Long customerId, @RequestBody CustomerInDto customerDetails) throws CustomerNotFoundException {
        CustomerOutDto updatedCustomer = customerService.partialUpdate(customerId, customerDetails);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Customer>> getCustomersByName(@RequestParam String name) {
        List<Customer> customers = customerService.getCustomersByName(name);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/countByRole")
    public ResponseEntity<Long> getCustomerCountByRole(@RequestParam String role) {
        long count = customerService.getCustomerCountByRole(role);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/joinedAfter")
    public ResponseEntity<List<Customer>> getCustomersJoinedAfter(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateJoined) {
        List<Customer> customers = customerService.getCustomersJoinedAfter(dateJoined);
        return ResponseEntity.ok(customers);
    }

    // Métodos para las consultas SQL nativas
    @GetMapping("/searchByNameNative")
    public ResponseEntity<List<Customer>> getCustomersByNameNative(@RequestParam String name) {
        List<Customer> customers = customerService.getCustomersByNameNative(name);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/countByRoleNative")
    public ResponseEntity<Long> getCustomerCountByRoleNative(@RequestParam String role) {
        long count = customerService.getCustomerCountByRoleNative(role);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/joinedAfterNative")
    public ResponseEntity<List<Customer>> getCustomersJoinedAfterNative(@RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateJoined) {
        List<Customer> customers = customerService.getCustomersJoinedAfterNative(dateJoined);
        return ResponseEntity.ok(customers);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception) {
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
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
//        ErrorResponse error = ErrorResponse.generalError(500, "Internal Server Error");
//        logger.error(exception.getMessage(), exception);
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
