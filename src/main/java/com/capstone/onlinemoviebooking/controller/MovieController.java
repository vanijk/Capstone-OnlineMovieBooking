package com.capstone.onlinemoviebooking.controller;

import com.capstone.onlinemoviebooking.service.MovieService;
import com.capstone.onlinemoviebooking.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.ServerException;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;
    @GetMapping("/getMovie")
    public ModelAndView getMovieDetails(String title, String trailerUrl, ModelMap map) {
        movieService.addMovie(title,trailerUrl);

        Movie movie = movieService.getMovieDetails(title);
        map.addAttribute(movie);
        return new ModelAndView("result", map);
    }
 /*   @GetMapping("/add-newmovie")
        public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-movie.html");
        return modelAndView;
    }*/
    @PostMapping("/add-movie")
    public String saveMovie(@RequestBody Movie movie){

        Movie movie1 = movieService.save(movie);
       /* if (movie == null) {
            System.out.println("error");
        } else {*/

        return "redirect:add-movie";
    }

}
