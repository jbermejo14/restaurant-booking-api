package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Beverage;
import com.example.restaurantreservationaa.domain.MenuItem;
import com.example.restaurantreservationaa.domain.dto.ErrorResponse;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageInDto;
import com.example.restaurantreservationaa.domain.dto.beverage.BeverageOutDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemInDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemOutDto;
import com.example.restaurantreservationaa.domain.dto.menuitem.MenuItemRegistrationDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderInDto;
import com.example.restaurantreservationaa.domain.dto.order.OrderOutDto;
import com.example.restaurantreservationaa.exception.BeverageNotFoundException;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.exception.MenuItemNotFoundException;
import com.example.restaurantreservationaa.exception.OrderNotFoundException;
import com.example.restaurantreservationaa.service.MenuItemService;
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
@RequestMapping("/menuitems")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;
    private final Logger logger = LoggerFactory.getLogger(MenuItemController.class);

    @GetMapping
    public ResponseEntity<List<MenuItemOutDto>> getAll(@RequestParam(value = "name", defaultValue = "") String name,
                                                @RequestParam(value = "description", defaultValue = "") String description)  {
        List<MenuItemOutDto> menuItems = menuItemService.getAll(name, description);
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

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> getMenuItemByFilter(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description ,
            @RequestParam(value = "category", required = false) String category) {

        List<MenuItem> menutiems = menuItemService.getMenuItemsByFilter(name, description, category);
        return new ResponseEntity<>(menutiems, HttpStatus.OK);
    }

    @PutMapping("/{menuItemId}")
    public ResponseEntity<MenuItemOutDto> modifyMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItemInDto menuItem) throws MenuItemNotFoundException {
        MenuItemOutDto modifiedMenuItem = menuItemService.modify(menuItemId, menuItem);
        return new ResponseEntity<>(modifiedMenuItem, HttpStatus.OK);
    }

    @PatchMapping("/{menuItemId}")
    public ResponseEntity<MenuItemOutDto> partialUpdateMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItemInDto menuItemInDto) throws MenuItemNotFoundException {
        MenuItemOutDto modifiedMenuItem = menuItemService.partialUpdate(menuItemId, menuItemInDto);
        return new ResponseEntity<>(modifiedMenuItem, HttpStatus.OK);
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMenuItemNotFoundException(MenuItemNotFoundException exception) {
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
