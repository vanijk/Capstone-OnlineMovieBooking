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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller

public class AdminController {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieRepositoryI movieRepositoryI;
    @Autowired
    ShowRepositoryI showRepositoryI;
    @Autowired
    ShowService showService;
    @Autowired
    TheaterRepositoryI theaterRepositoryI;
    @Autowired
    ScreenRepositoryI screenRepositoryI;
    @GetMapping("/admin/asign-page/{id}")
    public String asignShow(@PathVariable("id") String title,Model model){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(title);
        List<Theatre> theatres = theaterRepositoryI.findAll();
        model.addAttribute("movieDTO",movieDTO);
        model.addAttribute("theaters",theatres);

        return "/admin-edit";
    }
    @GetMapping("/admin/edit-page/{id}")
    public String editShow(@PathVariable("id") String title,Model model){
        MovieDTO movieDTO = movieService.getMovieDTOByMovie(title);
        movieDTO.setTitle(title);
        model.addAttribute("movieDTO",movieDTO);

        return "/admin-edit-show";

    }
    @GetMapping("/admin/addShowColumn/{id}")
    public String addShowColumn(@PathVariable("id") String title,Model model){
        MovieDTO movieDTO = movieService.getMovieDTOByMovie(title);
        movieDTO.setTitle(title);
        model.addAttribute("movieDTO",movieDTO);
        List<Show> shows = movieDTO.getShows();
        shows.add(new Show());
        movieDTO.setShows(shows);
        /*for (int i = 1; i <=  movieDTO.getShows().size() +1; i++) {
            movieDTO.addShow(new Show());
        }*/

        return "/admin-edit-show";

    }

    @GetMapping("/admin/edit-page")
    public String asignShow( String title, long theaterId,Model model){

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(title);
        movieDTO.setTheatreId(theaterId);

        List<Show> shows = new ArrayList<>();
        movieDTO.setShows(shows);
        for (int i = 1; i <= 5; i++) {
            movieDTO.addShow(new Show());
        }

        List<Screen> screens = (List<Screen>) screenRepositoryI.findByThaeterId(theaterId);
        movieDTO.setTheaterName(screens.get(0).getTheatre().getTheatreName());
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
   @GetMapping("/admin-add-movie")
    public String addMovies(String title,String trailerUrl,Model model){
        movieService.addMovie(title,trailerUrl);

        return "redirect:/admin-page";

    }
    @GetMapping("admin/deleteby-movieId/{id}")
    public String deleteByMovieId(@PathVariable("id") String title,Model model){
        movieRepositoryI.deleteByMovieTitle(title);

        List<MovieDTO> movieDTOS = movieService.getMoviesDTO();
        model.addAttribute("moviesDTOS", movieDTOS);

        return "redirect:/admin-page";
    }

    @GetMapping("admin/deleteMovieAndShows/{id}")
    public String deleteMovieAndShows(@PathVariable("id") String title,Model model){
        showService.deleteShowsByMovie(title);
        movieRepositoryI.deleteByMovieTitle(title);

        List<MovieDTO> movieDTOS = movieService.getMoviesDTO();
        model.addAttribute("moviesDTOS", movieDTOS);

        return "redirect:/admin-page";
    }

    @GetMapping("/admin/deleteby-showId/{id}/title/{id1}")
    public String deleteShowsById(@PathVariable("id") long showId,@PathVariable("id1") String title,Model model){
        showRepositoryI.deleteShowBYId(showId);
        MovieDTO movieDTO = movieService.getMovieDTOByMovie(title);
        if(movieDTO.getShows().size()== 0)
            return "redirect:/admin-page";
        movieDTO.setTitle(title);
        model.addAttribute("movieDTO",movieDTO);



        return "/admin-edit-show";

    }
    @GetMapping("/admin/add-newmovie")
    public String addNewMovie(Model model) {


        return"add-movie";
    }
    @GetMapping("admin/saveMovie")
    public String getMovieDetails(String title, String trailerUrl, Model model) {
        movieService.addMovie(title,trailerUrl);
        return "redirect:/admin-page";
    }
    @PostMapping("/admin/add-movie")
    public String saveMovie(@RequestBody Movie movie){

        Movie movie1 = movieService.save(movie);
       /* if (movie == null) {
            System.out.println("error");
        } else {*/

        return "redirect:/admin-page";
    }


}
