package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.dto.ScreeningMovie;
import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Screen;
import com.capstone.onlinemoviebooking.model.Show;
import com.capstone.onlinemoviebooking.service.ScreeningService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.params.ParameterizedTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ScreeningRepositoryTest {
    @Autowired
    ScreeningRepositoryI screeningRepositoryI;
  //  @Autowired
  //  ScreeningService screeningService;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /*get all shows screening by todat and also get by theater */
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 5, -3, 15, Long.MAX_VALUE})
    public void findAllByStartDateLessThanEqual(long number){
        Date currentDate = Date.valueOf(LocalDate.now());
        Date currentDate1 = currentDate;
        List<Show> shows = screeningRepositoryI.FindAllwithNative(number,currentDate,currentDate1);
        System.out.println(shows.size());
        assertNotNull(shows);
        assertNotEquals(shows.size(), 0);
    }


  /*  @Test
    public void findDistinctByMovie(){
        Date currentDate = Date.valueOf(LocalDate.now());
        Date currentDate1 = currentDate;
        List<Object> movies = screeningRepositoryI.findDistinctMoviesByTheaterId(1l,currentDate,currentDate1);
        System.out.println(movies.size());
        assertNotNull(movies);
        assertNotEquals(movies.size(), 0);
    }
   @Test
    public void findDistinctByScreen(){
       Date currentDate = Date.valueOf(LocalDate.now());
       Date currentDate1 = currentDate;
        List<Object> screens = screeningRepositoryI.findDistinctScreensByTheaterId(1l,currentDate,currentDate1);
        System.out.println(screens.size());
        assertNotNull(screens);
        assertNotEquals(screens.size(), 0);
    }

   @Test
    public void getMoviesByTheterIdAndDate(){
        Date currentDate = Date.valueOf(LocalDate.now());
        Date currentDate1 = currentDate;
        List<ScreeningMovie> sm = screeningService.getMoviesByTheterIdAndDate(1l,currentDate,currentDate1);
        System.out.println(sm.size());
        assertNotNull(sm);
        assertNotEquals(sm.size(), 0);
    }
*/
}
