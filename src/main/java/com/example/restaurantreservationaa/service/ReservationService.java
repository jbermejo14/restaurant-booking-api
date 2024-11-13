package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.domain.Reservation;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.exception.ReservationNotFoundException;
import com.example.restaurantreservationaa.repository.CustomerRepository;
import com.example.restaurantreservationaa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<Reservation> getAll() {
        List<Reservation> allReservations = reservationRepository.findAll();
        return allReservations;
    }

    public Reservation get(long id) throws ReservationNotFoundException {
        return reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
    }

    public Reservation add(long customerId, Reservation reservation) throws CustomerNotFoundException  {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        reservation.setCustomer(customer);
        return reservationRepository.save(reservation);
    }

    public void remove(long reservationId) throws ReservationNotFoundException {
        reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.deleteById(reservationId);
    }
}
