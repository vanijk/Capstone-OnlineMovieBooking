package com.capstone.onlinemoviebooking.controller;

import com.capstone.onlinemoviebooking.dto.MovieDTO;
import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Screen;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.model.Theatre;
import com.capstone.onlinemoviebooking.repository.MovieRepositoryI;
import com.capstone.onlinemoviebooking.repository.ScreenRepositoryI;
import com.capstone.onlinemoviebooking.repository.ShowRepositoryI;
import com.capstone.onlinemoviebooking.repository.TheaterRepositoryI;
import com.capstone.onlinemoviebooking.service.MovieService;
import com.capstone.onlinemoviebooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller

public class AdminController {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieRepositoryI movieRepositoryI;
    @Autowired
    ShowService showService;
    @Autowired
    TheaterRepositoryI theaterRepositoryI;
    @Autowired
    ScreenRepositoryI screenRepositoryI;
    @GetMapping("/admin/edit-page/{id}")
    public String editShow(@PathVariable("id") String title,Model model){
        //Movie movie = movieRepositoryI.getReferenceByTitle(title);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(title);
        List<Theatre> theatres = theaterRepositoryI.findAll();
       // List<Screen> screens = (List<Screen>) screenRepositoryI.findAll();
      //  model.addAttribute("movie",movie);
        model.addAttribute("movieDTO",movieDTO);
        model.addAttribute("theaters",theatres);
        //model.addAttribute("screens",screens);
        return "/admin-edit";
    }
    @GetMapping("/admin/edit-page")
    public String editShow( String title, long theaterId,Model model){

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(title);
        movieDTO.setTheatreId(theaterId);
        List<Show> shows = new ArrayList<>();
        movieDTO.setShows(shows);
        for (int i = 1; i <= 5; i++) {
            movieDTO.addShow(new Show());
        }

        List<Screen> screens = (List<Screen>) screenRepositoryI.findByThaeterId(theaterId);
        model.addAttribute("movieDTO",movieDTO);
        model.addAttribute("screens",screens);
        return "/admin-edit";
    }
    @PostMapping("/admin/UpdateShow")
    public String updateShow(MovieDTO movieDto,Model model){
        showService.updateShow(movieDto);
        return "redirect:/admin-page";
    }

    @GetMapping("/admin-page")
    public String getAdmin(Model model){
        List<MovieDTO> movieDTOS = movieService.getMoviesDTO();
        model.addAttribute("moviesDTOS", movieDTOS);
        return "/admin-page";
    }
 /*   @GetMapping("/admin-add-movie")
    public String addMovies(String title,String trailerUrl,Model model){
        movieService.addMovie(title,trailerUrl);
       // Movie movie = movieService.getMovieDetails(title);
        //model.addAttribute(movie);
        return "redirect:/admin-page";
        //return "/add-movie";
    }*/
    @GetMapping("admin/deleteby-movieId/{id}")
    public String deleteByMovieId(@PathVariable("id") String title,Model model){
        movieRepositoryI.deleteByMovieTitle(title);

        List<MovieDTO> movieDTOS = movieService.getMoviesDTO();
        model.addAttribute("moviesDTOS", movieDTOS);

        return "redirect:/admin-page";
    }

    @GetMapping("admin/deleteMovieAndShows/{id}")
    public String deleteMovieAndShows(@PathVariable("id") String title,Model model){
        movieRepositoryI.deleteByMovieTitle(title);

        List<MovieDTO> movieDTOS = movieService.getMoviesDTO();
        model.addAttribute("moviesDTOS", movieDTOS);

        return "redirect:/admin-page";
    }


}
