package com.capstone.onlinemoviebooking.controller;

import com.capstone.onlinemoviebooking.model.Seat;
import com.capstone.onlinemoviebooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;
    @GetMapping ("/getSeats")
    public String getSeats(Long screen) {
        List<Seat> seats = bookingService.seatMap(screen);
     //   model.addAttribute(seats);
        return "seatMap";
    }


}
