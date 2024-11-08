package com.example.restaurantreservationaa.repository;

import com.example.restaurantreservationaa.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findAll();
    List<Reservation> findByName(String name);
    List<Reservation> findByEmail(String email);
}
