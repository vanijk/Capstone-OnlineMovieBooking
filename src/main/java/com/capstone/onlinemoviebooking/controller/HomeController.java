package com.capstone.onlinemoviebooking.controller;

import com.capstone.onlinemoviebooking.dto.ScreeningMovie;
import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Screen;
import com.capstone.onlinemoviebooking.service.MovieService;
import com.capstone.onlinemoviebooking.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    MovieService movieService;
    @Autowired
    ScreeningService screeningService;
    List<ScreeningMovie> screeningMovies = null;
    List<Movie> movies = null;

     @GetMapping("/")
     public String getMoviesByToday(Model model) {
         Date currentDate = Date.valueOf(LocalDate.now());
         screeningMovies = screeningService.getMoviesByTheterIdAndDate(1l,currentDate,currentDate);
         movies = movieService.getMovies();
         model.addAttribute("screeningMovies",screeningMovies);
         model.addAttribute("movies",movies);
         return "index";
    }
    @GetMapping("/index")
    public String getMoviesByTodayIndex(Model model) {
        Date currentDate = Date.valueOf(LocalDate.now());
        screeningMovies = screeningService.getMoviesByTheterIdAndDate(1l,currentDate,currentDate);
        movies = movieService.getMovies();
        model.addAttribute("screeningMovies",screeningMovies);
        model.addAttribute("movies",movies);
        return "index";
    }
    @GetMapping("/getMoviesByDate")
    public String getMoviesByDate(@RequestParam String date,Model model) {
        System.out.println("Selected date"+ date);
        Date currentDate = Date.valueOf(date);
        System.out.println("Formated date"+ date);
        screeningMovies = screeningService.getMoviesByTheterIdAndDate(1l,currentDate,currentDate);

        movies = movieService.getMovies();
        model.addAttribute("screeningMovies",screeningMovies);
        model.addAttribute("movies",movies);
        return "index";
    }

}
