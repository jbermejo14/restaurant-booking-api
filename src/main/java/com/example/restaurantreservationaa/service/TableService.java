package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Table;
import com.example.restaurantreservationaa.exception.ReservationNotFoundException;
import com.example.restaurantreservationaa.exception.TableNotFoundException;
import com.example.restaurantreservationaa.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public List<Table> getAll() {
        List<Table> allTables = tableRepository.findAll();
        return allTables;
    }

    public Table get(long id) throws TableNotFoundException {
        return tableRepository.findById(id).orElseThrow(TableNotFoundException::new);
    }

    public Table add(Table table) {
        return tableRepository.save(table);
    }

    public void remove(long tableId) throws TableNotFoundException {
        tableRepository.findById(tableId).orElseThrow(TableNotFoundException::new);
        tableRepository.deleteById(tableId);
    }
}
