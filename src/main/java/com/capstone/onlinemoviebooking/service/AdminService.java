package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.dto.Booking;
import com.capstone.onlinemoviebooking.dto.MovieDTO;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.model.Theatre;
import com.capstone.onlinemoviebooking.repository.ScreenRepositoryI;
import com.capstone.onlinemoviebooking.repository.ShowRepositoryI;
import com.capstone.onlinemoviebooking.repository.TheaterRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private ShowRepositoryI showRepositoryI;
    @Autowired
    private TheaterRepositoryI theaterRepositoryI;
    @Autowired
    private ScreenRepositoryI screenRepositoryI;
    public MovieDTO getMovieDTO(){
        MovieDTO movieDTO = new MovieDTO();
       /* List<Theatre> theatres = theaterRepositoryI.findAll();
        for(Theatre theatre : theatres) {
            movieDTO.setTheatreId( theatre.getTheatreId());
            movieDTO.setTheaterName(theatre.getTheatreName());
            showRepositoryI.
        }*/
       return movieDTO;
    }
}
