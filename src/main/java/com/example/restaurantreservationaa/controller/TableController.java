package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Table;
import com.example.restaurantreservationaa.exception.TableNotFoundException;
import com.example.restaurantreservationaa.repository.TableRepository;
import com.example.restaurantreservationaa.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("/tables")
    public ResponseEntity<List<Table>> getAll() {
        return new ResponseEntity<>(tableService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/tables/:tableId")
    public ResponseEntity<Table> getTable(long tableId)  throws TableNotFoundException {
        Table table = tableService.get(tableId);
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @PostMapping("/tables")
    public ResponseEntity<Table> addTable(@RequestBody Table table) {
        return new ResponseEntity<>(tableService.add(table), HttpStatus.CREATED);
    }

    @DeleteMapping("/table/:tableId")
    public ResponseEntity<Void> removeTable(long tableId) throws TableNotFoundException {
        tableService.remove(tableId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleTableNotFoundException(TableNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
