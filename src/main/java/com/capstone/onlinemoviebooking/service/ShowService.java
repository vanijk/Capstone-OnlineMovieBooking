package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.dto.MovieDTO;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.repository.MovieRepositoryI;
import com.capstone.onlinemoviebooking.repository.ScreenRepositoryI;
import com.capstone.onlinemoviebooking.repository.ShowRepositoryI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
               show.setStartDate(movieDTO.getStartDate());
               show.setEndDate((movieDTO.getEndDate()));
               show.setMovie(movieRepositoryI.getReferenceByTitle(movieDTO.getTitle()));
               show.setScreen(screenRepositoryI.findById(movieDTO.getScreenId()).orElse(null));
               showRepositoryI.save(show);
           }
       }catch (Exception e){
           log.error("Error while creating Show."+e.getMessage());
           e.printStackTrace();
       }

        log.info("Update Show is successfull");
    }
}
