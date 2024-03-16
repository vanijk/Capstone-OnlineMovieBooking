package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.model.Seat;
import com.capstone.onlinemoviebooking.repository.BookingRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepositoryI bookingRepositoryI;

    public List<Seat> seatMap(Long screen){
        List<Seat> seats = bookingRepositoryI.findByScreen(screen);
        return seats;
    }
}
