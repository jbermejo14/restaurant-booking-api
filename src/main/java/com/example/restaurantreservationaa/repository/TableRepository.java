package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends CrudRepository<Table, Long> {
    List<Table> findAll();
    List<Table> findByTableNum(int tableNum);
    List<Table> findBySeatingCapacity(int seatingCapacity);
}
