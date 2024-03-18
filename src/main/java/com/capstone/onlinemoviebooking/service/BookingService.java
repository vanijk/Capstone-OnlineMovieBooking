package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.dto.Booking;
import com.capstone.onlinemoviebooking.model.Seat;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.repository.BookingRepositoryI;
import com.capstone.onlinemoviebooking.repository.SeatMapRepositoryI;
import com.capstone.onlinemoviebooking.repository.ShowRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepositoryI bookingRepositoryI;
    @Autowired
    private SeatMapRepositoryI seatMapRepositoryI;
    @Autowired
    private ShowRepositoryI showRepositoryI;

    public List<Seat> seatMap(Long screen){
        List<Seat> seats = bookingRepositoryI.findByScreen(screen);
        return seats;
    }

    public Booking getBooking(long theatreId, Date selectedDate, long showId){
        Booking booking = new Booking();
        Optional<Show> show = showRepositoryI.findById(showId);
        Show sh = show.orElse(null);
        System.out.println( sh.getMovie().getTitle());

        String title = sh.getMovie().getTitle();
        long screenId = sh.getScreen().getScreenId();
        String screenName = sh.getScreen().getScreenName();
        double ticketPrice = sh.getScreen().getTicketPrice();
        String showTime = sh.getShowTime();
        String theaterName = sh.getScreen().getTheatre().getTheatreName();
        String theaterAddress = sh.getScreen().getTheatre().getTheatreAddress();
        booking.setShowId(showId);
        booking.setSelectedDate(selectedDate);
        booking.setTheatreId(theatreId);
        booking.setTitle(title);
        booking.setScreenId(screenId);
        booking.setScreenName(screenName);
        booking.setTicketPrice(ticketPrice);
        booking.setShowTime(showTime);
        booking.setTheaterName(theaterName);
        booking.setTheaterAddress(theaterAddress);
        booking.setNumberOfTickets(0);
        booking.setSeatNumber("");
        return booking;
    }
}
