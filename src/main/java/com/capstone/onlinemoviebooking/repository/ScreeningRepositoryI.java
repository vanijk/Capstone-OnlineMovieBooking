package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Screen;
import com.capstone.onlinemoviebooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ScreeningRepositoryI extends JpaRepository<Show,Long> {
    List<Show> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date d,Date d2);
    @Query("from Show sh join Screen s on s.screenId = sh.screen.screenId where s.theatre.theatreId =:theaterId and sh.startDate <= :d1 and sh.endDate >= :d2 ")
    List<Show> FindAllwithNative(long theaterId,Date d1,Date d2);
    @Query("select s.screenId,s.screenName from Show sh join Screen s on s.screenId = sh.screen.screenId where s.theatre.theatreId =:theaterId and sh.startDate <= :d1 and sh.endDate >= :d2 ")
    List<Object> findDistinctScreensByTheaterId(long theaterId,Date d1,Date d2);
    @Query("select m.title,m.country,m.genre,m.imageData,m.language,m.plot,m.poster,m.released,m.runtime,m.rated,m.trailerUrl from Show sh join Screen s on s.screenId = sh.screen.screenId join Movie m on m.title = sh.movie.title where s.theatre.theatreId =:theaterId and sh.startDate <= :d1 and sh.endDate >= :d2 ")
    List<Object> findDistinctMoviesByTheaterId(long theaterId,Date d1,Date d2);
}
