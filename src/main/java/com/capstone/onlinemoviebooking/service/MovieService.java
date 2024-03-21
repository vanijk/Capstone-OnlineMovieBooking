package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Screen;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.repository.MovieRepositoryI;
import com.capstone.onlinemoviebooking.repository.ScreenRepositoryI;
import com.capstone.onlinemoviebooking.repository.ShowRepositoryI;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.List;
import java.net.URL;
import java.util.Optional;

@Service
public class MovieService {


    @Autowired
    private MovieRepositoryI movieRepositoryI;
    @Autowired
    private ShowRepositoryI showRepositoryI;
    @Autowired
    private ScreenRepositoryI screenRepositoryI;
    private static final String API_URL = "http://www.omdbapi.com/?apikey=b79fdda2&t=";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public void addMovie(String title,String trailerUrl) {
        try {
            String url = API_URL + title;
            RestTemplate rt = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Movie movie = mapper.readValue(rt.getForObject(url, String.class), Movie.class);
            System.out.println(movie.toString());

            URL url1 = new URL(movie.getPoster());
            Image image = ImageIO.read(url1);
            byte[] content = url1.openStream().readAllBytes();
            movie.setImageData(content);
            movie.setTrailerUrl(trailerUrl);
            movieRepositoryI.save(movie);

        }catch(Exception e){
            System.out.println("Exception occured :"+e);
        }


    }

    public Movie getMovieDetails(String title){

        return movieRepositoryI.getReferenceByTitle(title);
    }
    public List<Movie> getMovies(){

        return movieRepositoryI.findAll();
    }
    public Movie save(Movie movie) {
        try {
            String url = API_URL + movie.getTitle();
            RestTemplate rt = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Movie movie1 = mapper.readValue(rt.getForObject(url, String.class), Movie.class);
            System.out.println(movie.toString());

            URL url1 = new URL(movie.getPoster());
            Image image = ImageIO.read(url1);
            byte[] content = url1.openStream().readAllBytes();
            movie.setImageData(content);
            movieRepositoryI.save(movie);
        }catch (Exception e){

        }
        movieRepositoryI.save(movie);
        return movie;
    }

    public String assignShow(String title,long screenId,String showTime,java.sql.Date starDate,java.sql.Date endDate){
      try {
          Movie movie = movieRepositoryI.getReferenceByTitle(title);
          Optional<Screen> screenOptional = screenRepositoryI.findById(screenId);
          Screen screen = screenOptional.orElse(null);
          showRepositoryI.save(new Show(movie, screen, showTime, starDate, endDate));
          return "added";
      }catch (Exception e){
          LOGGER.error("assignShow failed"+e.getMessage());
          e.printStackTrace();
          return "failed";
      }
    }

}
