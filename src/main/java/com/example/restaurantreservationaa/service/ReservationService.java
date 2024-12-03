package com.example.restaurantreservationaa.service;

import com.example.restaurantreservationaa.domain.Customer;
import com.example.restaurantreservationaa.domain.Reservation;
import com.example.restaurantreservationaa.domain.dto.ReservationInDto;
import com.example.restaurantreservationaa.domain.dto.ReservationOutDto;
import com.example.restaurantreservationaa.exception.CustomerNotFoundException;
import com.example.restaurantreservationaa.exception.ReservationNotFoundException;
import com.example.restaurantreservationaa.repository.CustomerRepository;
import com.example.restaurantreservationaa.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationOutDto> getAll() {
        List<Reservation> allReservations = reservationRepository.findAll();
        List<ReservationOutDto> reservationOutDtos = modelMapper.map(allReservations, new TypeToken<List<ReservationOutDto>>() {}.getType());
        return reservationOutDtos;
    }

    public Reservation get(long id) throws ReservationNotFoundException {
        return reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new);
    }

    public Reservation add(long customerId, ReservationInDto reservationInDto) throws CustomerNotFoundException  {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Reservation reservation = modelMapper.map(reservationInDto, Reservation.class);

        reservation.setCustomer(customer);
        return reservationRepository.save(reservation);
    }

    public void remove(long reservationId) throws ReservationNotFoundException {
        reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
        reservationRepository.deleteById(reservationId);
    }
}
