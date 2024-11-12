package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();
    List<Customer> findByName(String name);
    List<Customer> findByEmail(String email);
}
