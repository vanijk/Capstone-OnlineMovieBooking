package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.dto.MovieDTO;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.repository.MovieRepositoryI;
import com.capstone.onlinemoviebooking.repository.ScreenRepositoryI;
import com.capstone.onlinemoviebooking.repository.ShowRepositoryI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShowService {
    @Autowired
    ShowRepositoryI showRepositoryI;
    @Autowired
    MovieRepositoryI movieRepositoryI;
    @Autowired
    ScreenRepositoryI screenRepositoryI;
    public void updateShow(MovieDTO movieDTO){
        log.info("Inside Update Show");
       try {
           for (Show show : movieDTO.getShows()) {
               if(!show.getShowTime().equals("") && show.getShowTime() != null) {
                   show.setStartDate(movieDTO.getStartDate());
                   show.setEndDate((movieDTO.getEndDate()));
                   show.setMovie(movieRepositoryI.getReferenceByTitle(movieDTO.getTitle()));
                   show.setScreen(screenRepositoryI.findById(movieDTO.getScreenId()).orElse(null));
                   showRepositoryI.save(show);
               }
           }
       }catch (Exception e){
           log.error("Error while creating Show."+e.getMessage());
           e.printStackTrace();
       }

        log.info("Update Show is successfull");
    }
    public void deleteShowsByMovie(String title){
        log.info("Inside delete Shows by movie");
        try{
            List<Show> shows = showRepositoryI.getShowsByMovieTitle(title);
            for(Show show : shows){
                showRepositoryI.deleteById(show.getShowId());
            }
        }catch (Exception e){
            log.error("Error while deleteing show."+e.getMessage());
            e.printStackTrace();
        }
    }
}
