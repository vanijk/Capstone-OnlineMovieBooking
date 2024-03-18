package com.capstone.onlinemoviebooking.dto;

import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Show;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Booking {
    private String title;
    private long theatreId;
    private String theaterName;
    private String theaterAddress;
    private long screenId;
    private String screenName;
    private double ticketPrice;
    private java.sql.Date selectedDate;
    private long showId;
    private String showTime;
    private int numberOfTickets;
    private String SeatNumber;
}
