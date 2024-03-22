package com.capstone.onlinemoviebooking.repository;


import com.capstone.onlinemoviebooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovieRepositoryI extends JpaRepository<Movie, Long> {

    @Query("FROM Movie  WHERE title like :title ")
    Movie getReferenceByTitle(String title);
    @Modifying
    @Transactional
    @Query( value = "DELETE from Movie  WHERE title like :title ",
            nativeQuery = true)
    void deleteByMovieTitle(String title);
    @Modifying
    @Transactional
    @Query( value = "DELETE from Movie  WHERE title like :title ",
            nativeQuery = true)
    void deleteMovieAndShows(String title);
}
