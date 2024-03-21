package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepositoryI extends JpaRepository<Show,Long> {
    @Query( value = "SELECT * FROM `show` sh WHERE sh.movie_title = :title ",
            nativeQuery = true)
    List<Show> getShowsByMovieTitle(String title);

}
