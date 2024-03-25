package com.capstone.onlinemoviebooking.controller;

import com.capstone.onlinemoviebooking.dto.Booking;
import com.capstone.onlinemoviebooking.model.SeatMap;
import com.capstone.onlinemoviebooking.repository.BookingRepositoryI;
import com.capstone.onlinemoviebooking.repository.SeatMapRepositoryI;
import com.capstone.onlinemoviebooking.service.BookingService;
import com.capstone.onlinemoviebooking.service.SeatMapService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping(value = "/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    SeatMapService seatMapService;
    @Autowired
    SeatMapRepositoryI seatMapRepositoryI;
    @Autowired
    BookingRepositoryI bookingRepositoryI;

    @GetMapping("/getSeats")
    public String getSeats(){
        return "/thankyou";
    }
    @PostMapping("/getSeats")
    public String getSeatsPost(Booking booking,ModelMap map){
       /* long theatreId = booking.getTheatreId();
        java.sql.Date selectedDate = booking.getSelectedDate();
        long showId = booking.getShowId();
        Booking booking1 = bookingService.getBooking(theatreId,selectedDate,showId);
        System.out.println(booking.getScreenName());
        System.out.println(booking.getTitle());
        System.out.println(booking.getTicketPrice());*/
        List<SeatMap> seats = seatMapRepositoryI.findAll();
        seats = seatMapService.setBookedSeats(seats,booking.getScreenId(),booking.getSelectedDate(),booking.getShowTime());
        List<List<SeatMap>> seatList = getSeatListOfList(seats);
        map.addAttribute("booking",booking);
        map.addAttribute("seatsOfSeats",seatList);
        return "booking";
    }
   /* @GetMapping("/getSeats")
    public String getSeats(long theatreId, Date selectedDate, long showId,ModelMap map){
       System.out.println("Theater ID :"+theatreId);
        System.out.println("Selected Date :"+selectedDate);
        System.out.println("Show ID :"+showId);
        List<SeatMap> seats = seatMapRepositoryI.findAll();
        seats = seatMapService.setBookedSeats(seats);
        List<List<SeatMap>> seatList = getSeatListOfList(seats);

        map.addAttribute("seatsOfSeats",seatList);
        return "booking";
    }*/
   /* @PostMapping("/getSeats")
    public String getSeatsPost(long theatreId, Date selectedDate, long showId,ModelMap map){
        System.out.println("Theater ID :"+theatreId);
        System.out.println("Selected Date :"+selectedDate);
        System.out.println("Show ID :"+showId);
        Booking booking = bookingService.getBooking(theatreId,selectedDate,showId);
        System.out.println(booking.getScreenName());
        System.out.println(booking.getTitle());
        System.out.println(booking.getTicketPrice());
        List<SeatMap> seats = seatMapRepositoryI.findAll();
        seats = seatMapService.setBookedSeats(seats);
        List<List<SeatMap>> seatList = getSeatListOfList(seats);
        map.addAttribute("booking",booking);
        map.addAttribute("seatsOfSeats",seatList);
        return "booking";
    }*/
    @PostMapping("/save-booking")
    public String getBookingDetails(Booking booking,Model model, HttpServletRequest request){
        System.out.println(booking.getSeatNumber());
        System.out.println(booking.getNumberOfTickets());
        Booking book = (Booking) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (book == null) {
            request.getSession().setAttribute("MY_SESSION_MESSAGES", booking);
        }
        model.addAttribute("booking",booking);
        return"login-booking";
    }
    public List<List<SeatMap>> getSeatListOfList(List<SeatMap> seats){
        List<List<SeatMap>> s1 = new ArrayList<>();
        List<SeatMap> s2 = new ArrayList<>();
        char c = seats.get(0).getRow();
        for(int i = 0; i< seats.size();i++){
            SeatMap sm = seats.get(i);
            if(c == sm.getRow()){
                s2.add(sm);
            }else{
                s1.add(s2);

                c = seats.get(i).getRow();
                s2 = new ArrayList<>();
                s2.add(sm);
            }
        }
        s1.add(s2);
        System.out.println(s2.size());
        return s1;
    }

}
