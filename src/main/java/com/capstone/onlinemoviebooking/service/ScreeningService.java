package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.dto.ScreeningMovie;
import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Screen;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.model.Theatre;
import com.capstone.onlinemoviebooking.repository.ScreenRepositoryI;
import com.capstone.onlinemoviebooking.repository.ScreeningRepositoryI;
import com.capstone.onlinemoviebooking.repository.TheaterRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScreeningService {
        @Autowired
        ScreeningRepositoryI screeningRepositoryI;
        @Autowired
        TheaterRepositoryI theaterRepositoryI;
       public List<Show> getMoviesByDate(long theaterId,java.sql.Date date,java.sql.Date date1) {
                List<Show> shows = screeningRepositoryI.FindAllwithNative(theaterId,date,date1);
                return shows;
       }
       public List<ScreeningMovie> getMoviesByTheterIdAndDate(long theaterId,java.sql.Date date,java.sql.Date date1){
           List<ScreeningMovie> screeningMovies = new ArrayList<>();
           List<Movie> movies = new ArrayList<>();
           List<Show> showsList = new ArrayList<>();
           ScreeningMovie sm = new ScreeningMovie();

           List<Show> shows = screeningRepositoryI.FindAllwithNative(theaterId,date,date1);

           Movie movie  = shows.get(0).getMovie();
           sm.setMovie(movie);

           List<Theatre> t = theaterRepositoryI.findAll();
           sm.setTheatreId(t.get(0).getTheatreId());
           sm.setTheaterName(t.get(0).getTheatreName());
           sm.setTheaterAddress(t.get(0).getTheatreAddress());


           Screen screen = shows.get(0).getScreen();
           sm.setScreenId(screen.getScreenId());
           sm.setScreenName(screen.getScreenName());
           sm.setTicketPrice(screen.getTicketPrice());
           sm.setDate(date);
          // screens.add(screen);
           for(Show show : shows){
                if(movie.equals(show.getMovie()) ){
                    showsList.add(show);
                    System.out.println("------ "+showsList.size());
                }else{
                    sm.setShows(showsList);
                    screeningMovies.add(sm);
                    sm = new ScreeningMovie();
                    movie = show.getMovie();
                    sm.setMovie(movie);
                    screen = show.getScreen();
                    sm.setScreenId(screen.getScreenId());
                    sm.setScreenName(screen.getScreenName());
                    sm.setTicketPrice(screen.getTicketPrice());
                    sm.setDate(date);
                    System.out.println(showsList.size());

                    showsList = new ArrayList<>();

                    showsList.add(show);
                }
           }
           sm.setShows(showsList);
           screeningMovies.add(sm);
           return screeningMovies;
       }

}
