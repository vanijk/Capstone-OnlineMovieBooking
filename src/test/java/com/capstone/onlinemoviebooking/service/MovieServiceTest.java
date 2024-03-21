package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Screen;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.repository.MovieRepositoryI;
import com.capstone.onlinemoviebooking.repository.ScreenRepositoryI;
import com.capstone.onlinemoviebooking.repository.ShowRepositoryI;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieServiceTest {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieRepositoryI movieRepositoryI;
    @Autowired
    ScreenRepositoryI screenRepositoryI;
    @Autowired
    ShowRepositoryI showRepositoryI;
    @Test
    public void assignShow(){
        Movie movie = movieRepositoryI.getReferenceByTitle("Ordinary Angels");
        Optional<Screen> screenOptional =screenRepositoryI.findById(1l);
        Screen screen = screenOptional.orElse(null);
        Show show = showRepositoryI.save(new Show(movie, screen, "2:30 PM",  java.sql.Date.valueOf("2025-03-01"),java.sql.Date.valueOf("2025-04-12")));
        System.out.println(show.getMovie());
        assertNotNull(show);

    }
    @Test
    public void updateShow() {
        Optional<Show> showOptional = showRepositoryI.findById(152l);
        Show show = showOptional.orElse(null);
        show.setShowTime("11:30 PM");
        show.setEndDate(java.sql.Date.valueOf("2027-04-12"));
        showRepositoryI.save(show);
        assertNotEquals(show.getShowTime(),"10:30 AM");
        assertNotEquals(show.getEndDate(),java.sql.Date.valueOf("2024-04-12"));
        assertNotNull(show);
    }
    @Test
    public void deleteShow(){
        Optional<Show> showOptional = showRepositoryI.findById(102l);
        Show show = showOptional.orElse(null);
        long c = showRepositoryI.count();

        showRepositoryI.deleteById(show.getShow_id());
        c = c - 1;
        System.out.println("After delete"+c);
        assertThat(showRepositoryI.count()).isEqualTo((c));

    }
    @Test
    public void findById(){

    }
    @Test
    public void getShowByMovieTitle(){
        List<Show> showList = showRepositoryI.getShowsByMovieTitle("Migration");
        assertNotEquals(showList.size(),0);
    }
    @Test
    public void getTheaterByShow(){

    }

    @Test
    public void getShowByScreenId(){

    }



}
