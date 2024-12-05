package com.example.restaurantreservationaa.domain.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInDto {
    private long id;
    private Date reservationDate;
    private int numOfGuests;
    private long customerId;
    private long tableId;
    private String status;
}
