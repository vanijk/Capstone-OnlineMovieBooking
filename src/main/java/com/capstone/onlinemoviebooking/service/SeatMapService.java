package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.model.BookedSeats;
import com.capstone.onlinemoviebooking.model.SeatMap;
import com.capstone.onlinemoviebooking.repository.BookedSeatsRepositoryI;
import com.capstone.onlinemoviebooking.repository.SeatMapRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatMapService {

    @Autowired
    BookedSeatsRepositoryI bookedSeatsRepository;
    @Autowired
    SeatMapRepositoryI seatMapRepositoryI;
   // int screenId = 1;
   // private java.sql.Date screeningDate = new java.sql.Date(2024,3,14);;
   // private String screeningTime = "10:30";
    public List<SeatMap> setBookedSeats(List<SeatMap> seats,long screenId, java.sql.Date screeningDate, String screeningTime){
        List<BookedSeats> bookedSeats = bookedSeatsRepository.findSeatsBookedByScreenDateTime(screenId,screeningDate,screeningTime);

        for(int i = 0; i< seats.size(); i++){
            String seatName =  String.valueOf(seats.get(i).getRow()) + String.valueOf(seats.get(i).getRowNumber());
            for(int j = 0; j < bookedSeats.size() ; j++){

                if(bookedSeats.get(j).getSeatName().equals(seatName)){
                    System.out.println(bookedSeats.get(j).getSeatName());
                    seats.get(i).setClassName("booked");
                }
            }
        }
        return seats;
    }
    public void setBookedSeatsfromBooking(long screenId,java.sql.Date screeningDate, String screeningTime,String seatNumber,long userId){
        System.out.println(seatNumber);
        if(seatNumber != null) {
            String[] arrOfStr = seatNumber.split(",");
            for(String s :arrOfStr){
                if(s != "" || s != null) {
                    bookedSeatsRepository.save(new BookedSeats(screenId,screeningDate,screeningTime,s,"booked",userId));
                }
            }
        }
        seatMapRepositoryI.setSeatsBack();
       /* List<BookedSeats> bookedSeats = bookedSeatsRepository.findSeatsBookedByScreenDateTime(screenId,screeningDate,screeningTime);

        for(int i = 0; i< seats.size(); i++){
            String seatName =  String.valueOf(seats.get(i).getRow()) + String.valueOf(seats.get(i).getRowNumber());
            for(int j = 0; j < bookedSeats.size() ; j++){

                if(bookedSeats.get(j).getSeatName().equals(seatName)){
                    System.out.println(bookedSeats.get(j).getSeatName());
                    seats.get(i).setClassName("booked");
                }
            }
        }
        return seats;*/
    }

}
