package com.capstone.onlinemoviebooking.repository;

import com.capstone.onlinemoviebooking.dto.MovieDTO;
import com.capstone.onlinemoviebooking.model.Movie;
import com.capstone.onlinemoviebooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShowRepositoryI extends JpaRepository<Show,Long> {
    @Query( value = "SELECT * FROM `show` sh WHERE sh.movie_title = :title ",
            nativeQuery = true)
    List<Show> getShowsByMovieTitle(String title);
    @Modifying
    @Transactional
    @Query( value = "delete from `Show` WHERE `show_id` = :id ",
                 nativeQuery = true)
    void deleteShowBYId(long id);


  // @Modifying
 //   @Transactional
   // @Query( value = "UPDATE from Movie  WHERE title like :title ",
   //         nativeQuery = true)
  //  void updateShow(Show show);

}
