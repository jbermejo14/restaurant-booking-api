package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Reservation;
import com.example.restaurantreservationaa.exception.ReservationNotFoundException;
import com.example.restaurantreservationaa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        List<Reservation> allReservations = reservationRepository.findAll();
        return allReservations;
    }

    public Reservation get(long id) throws ReservationNotFoundException {
        return reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
    }

    public Reservation add(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void remove(long passengerId) throws ReservationNotFoundException {
        reservationRepository.findById(passengerId).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.deleteById(passengerId);
    }
}
